package com.ryankoech.krypto.feature_home.domain.usecase

import com.ryankoech.krypto.common.core.util.Resource
import com.ryankoech.krypto.common.presentation.util.DisplayCurrency
import com.ryankoech.krypto.feature_home.data.dto.display_currency.DisplayCurrencyDto
import com.ryankoech.krypto.feature_home.domain.repository.OwnedCoinsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import java.util.concurrent.Flow
import javax.inject.Inject

class GetDisplayCurrencyData @Inject constructor(
    private val repository: OwnedCoinsRepository
) {

    private val defaultList = listOf( DisplayCurrencyDto(DisplayCurrency.USD, 1.0))

    operator fun invoke() = flow<Resource<List<DisplayCurrencyDto>>> {
        val currencyData = repository.getDisplayCurrencyData() ?: defaultList
        emit(Resource.Success(currencyData))
    }.onStart {
        emit(Resource.Success(defaultList))
    }.catch { e ->
        e.printStackTrace()
        emit(Resource.Error(e.localizedMessage ?: "Unexpected Error Occurred", defaultList))
    }.flowOn(Dispatchers.IO)
}
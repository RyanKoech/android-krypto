package com.ryankoech.krypto.feature_coin_list.domain.usecase

import com.ryankoech.krypto.common.core.util.Resource
import com.ryankoech.krypto.common.presentation.util.DisplayCurrency
import com.ryankoech.krypto.feature_coin_list.core.di.HILT_NAME_REPO_FOR_ALL
import com.ryankoech.krypto.feature_coin_list.data.dto.display_currency.DisplayCurrencyDto
import com.ryankoech.krypto.feature_coin_list.domain.repository.CoinRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import org.jetbrains.annotations.VisibleForTesting
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named

class GetDisplayCurrencyDataUseCase @Inject constructor(
    @Named(HILT_NAME_REPO_FOR_ALL) private val repository: CoinRepository
) {

    @VisibleForTesting
    internal val defaultList = listOf( DisplayCurrencyDto(DisplayCurrency.USD, 1.0))

    operator fun invoke() = flow<Resource<List<DisplayCurrencyDto>>> {
        val displayCurrenciesDto = repository.getDisplayCurrencyData()
        val currencyData = if (displayCurrenciesDto.isNullOrEmpty())  defaultList  else displayCurrenciesDto
        emit(Resource.Success(currencyData))
    }.onStart {
        emit(Resource.Success(defaultList))
    }.catch { e ->
        Timber.e(e)
        emit(Resource.Error(e.localizedMessage ?: "Unexpected Error Occurred", defaultList))
    }.flowOn(Dispatchers.IO)
}
package com.ryankoech.krypto.feature_home.domain.usecase

import com.ryankoech.krypto.common.core.util.Resource
import com.ryankoech.krypto.common.presentation.util.DisplayCurrency
import com.ryankoech.krypto.feature_home.domain.repository.OwnedCoinsRepository
import javax.inject.Inject

class SaveDisplayCurrencyData @Inject constructor(
    private val repository: OwnedCoinsRepository
) {

    suspend operator fun invoke(displayCurrencyData: HashMap<DisplayCurrency, Double>) : Resource<Unit> =
        try {
            Resource.Success(repository.saveDisplayCurrencyData(displayCurrencyData))
        }catch (e : Throwable) {
            e.printStackTrace()
            Resource.Error(e.localizedMessage ?: "An Unexpected Error Occurred")
        }


}
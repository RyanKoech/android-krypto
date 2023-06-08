package com.ryankoech.krypto.feature_coin_list.domain.usecase

import com.ryankoech.krypto.common.core.util.Resource
import com.ryankoech.krypto.common.presentation.util.DisplayCurrency
import com.ryankoech.krypto.feature_coin_list.core.di.HILT_NAME_REPO_FOR_ALL
import com.ryankoech.krypto.feature_coin_list.domain.repository.CoinRepository
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named

class SaveDisplayCurrencyDataUseCase @Inject constructor(
    @Named(HILT_NAME_REPO_FOR_ALL) private val repository: CoinRepository
) {

    suspend operator fun invoke(displayCurrencyData: HashMap<DisplayCurrency, Double>) : Resource<Unit> =
        try {
            Resource.Success(repository.saveDisplayCurrencyData(displayCurrencyData))
        }catch (e : Throwable) {
            Timber.e(e)
            Resource.Error(e.localizedMessage ?: "An Unexpected Error Occurred")
        }


}
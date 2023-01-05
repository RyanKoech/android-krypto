package com.ryankoech.krypto.feature_home.domain.usecase

import com.ryankoech.krypto.common.core.util.Resource
import com.ryankoech.krypto.feature_home.core.di.HILT_NAME_REPO_FOR_ALL
import com.ryankoech.krypto.feature_home.data.dto.owned_coin.OwnedCoinDto
import com.ryankoech.krypto.feature_home.domain.repository.OwnedCoinsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named

class SaveOwnedCoinUseCase @Inject constructor(
    @Named(HILT_NAME_REPO_FOR_ALL) private val repository : OwnedCoinsRepository
) {

    suspend operator fun invoke(coin : OwnedCoinDto) : Resource<String> =
        try {
            val id = repository.saveOwnedCoin(coin)
            Resource.Success(
                data = id
            )
        }catch (e : Exception) {
            Timber.e(e)
            Resource.Error(e.message?: "Unexpected Error Occurred" )
        }
}
package com.ryankoech.krypto.feature_home.domain.usecase

import com.ryankoech.krypto.common.core.util.Resource
import com.ryankoech.krypto.feature_home.data.dto.owned_coin.OwnedCoinDto
import com.ryankoech.krypto.feature_home.domain.repository.OwnedCoinsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SaveOwnedCoinUseCase(
    private val repository : OwnedCoinsRepository
) {

    suspend operator fun invoke(coin : OwnedCoinDto) : Resource<Long> {
        return withContext(Dispatchers.IO) {
            try {
                val id = repository.saveOwnedCoin(coin)
                Resource.Success(
                    data = id
                )
            }catch (e : Exception) {
                Resource.Error(
                    e.message?: "Unexpected Error Occurred"
                )
            }
        }
    }

}
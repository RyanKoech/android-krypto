package com.ryankoech.krypto.feature_home.domain.usecase

import com.ryankoech.krypto.common.core.util.Resource
import com.ryankoech.krypto.feature_home.data.dto.owned_coin.OwnedCoinDto
import com.ryankoech.krypto.feature_home.domain.repository.OwnedCoinsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class GetOwnedCoinsUseCase(
    private val repository: OwnedCoinsRepository
) {

    suspend operator fun invoke() : Flow<Resource<List<OwnedCoinDto>>> {
        return flow {
            emit(
                Resource.Success(
                    data = repository.getOwnedCoins()
                )
            )
        }.onStart {
            Resource.Loading(data = null)
        }.catch { e ->
            Resource.Error<List<OwnedCoinDto>>(
                message = e.message ?: "Unexpected Error Occurred"
            )
        }.flowOn(Dispatchers.IO)
    }

}
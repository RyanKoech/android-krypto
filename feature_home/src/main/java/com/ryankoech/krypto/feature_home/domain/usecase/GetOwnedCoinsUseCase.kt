package com.ryankoech.krypto.feature_home.domain.usecase

import com.ryankoech.krypto.common.core.util.Resource
import com.ryankoech.krypto.feature_home.data.dto.owned_coin.OwnedCoinDto
import com.ryankoech.krypto.feature_home.domain.repository.OwnedCoinsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class GetOwnedCoinsUseCase @Inject constructor(
    private val repository: OwnedCoinsRepository
) {

    operator fun invoke() = flow<Resource<List<OwnedCoinDto>>> {
            emit(Resource.Success(repository.getOwnedCoins()))
        }.onStart {
            emit(Resource.Loading())
        }.catch { e ->
            emit(Resource.Error(e.localizedMessage ?: "Unexpected Error Occurred"))
        }.flowOn(Dispatchers.IO)
}
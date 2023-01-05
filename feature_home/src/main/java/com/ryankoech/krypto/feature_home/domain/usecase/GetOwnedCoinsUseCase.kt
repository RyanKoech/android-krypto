package com.ryankoech.krypto.feature_home.domain.usecase

import com.ryankoech.krypto.common.core.util.Resource
import com.ryankoech.krypto.feature_home.core.di.HILT_NAME_REPO_FOR_ALL
import com.ryankoech.krypto.feature_home.data.dto.owned_coin.OwnedCoinDto
import com.ryankoech.krypto.feature_home.domain.repository.OwnedCoinsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named

class GetOwnedCoinsUseCase @Inject constructor(
    @Named(HILT_NAME_REPO_FOR_ALL) private val repository: OwnedCoinsRepository
) {

    operator fun invoke() = flow<Resource<List<OwnedCoinDto>>> {
        emit(Resource.Success(repository.getOwnedCoins()))
    }.onStart {
        emit(Resource.Loading())
    }.catch { e ->
        Timber.e(e)
        emit(Resource.Error(e.localizedMessage ?: "Unexpected Error Occurred"))
    }.flowOn(Dispatchers.IO)
}
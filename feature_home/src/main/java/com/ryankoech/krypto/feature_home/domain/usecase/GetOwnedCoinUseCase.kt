package com.ryankoech.krypto.feature_home.domain.usecase

import com.ryankoech.krypto.common.core.util.Resource
import com.ryankoech.krypto.feature_home.core.di.HILT_NAME_REPO_FOR_ALL
import com.ryankoech.krypto.feature_home.data.dto.owned_coin.OwnedCoinDto
import com.ryankoech.krypto.feature_home.domain.repository.OwnedCoinsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named

class GetOwnedCoinUseCase @Inject constructor(
    @Named(HILT_NAME_REPO_FOR_ALL) private val repository: OwnedCoinsRepository
) {

    operator fun invoke(coinId : String) = flow<Resource<OwnedCoinDto>> {
        Timber.d("GetOwnedCoinUseCase")
        emit(Resource.Success(repository.getOwnedCoin(coinId)))
    }.onStart {
        emit(Resource.Loading())
    }.catch { e ->
        Timber.e(e)
        emit(Resource.Error(e.localizedMessage ?: "An Unknown Error Occured"))
    }.flowOn(Dispatchers.IO)

}
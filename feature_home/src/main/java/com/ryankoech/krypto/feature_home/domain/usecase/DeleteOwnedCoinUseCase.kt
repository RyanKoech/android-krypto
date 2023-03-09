package com.ryankoech.krypto.feature_home.domain.usecase

import com.ryankoech.krypto.common.core.util.Resource
import com.ryankoech.krypto.feature_home.core.di.HILT_NAME_REPO_FOR_ALL
import com.ryankoech.krypto.feature_home.domain.repository.OwnedCoinsRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject
import javax.inject.Named

class DeleteOwnedCoinUseCase @Inject constructor(
    @Named(HILT_NAME_REPO_FOR_ALL) private val repository: OwnedCoinsRepository,
){

    operator fun invoke(coinId : String) = flow<Resource<Unit>>{
        emit(Resource.Success(repository.deleteOwnedCoin(coinId)))
    }.onStart {
        emit(Resource.Loading())
    }.catch {e ->
        emit(Resource.Error(e.localizedMessage ?: "An Unknown Error Occurred"))
    }

}
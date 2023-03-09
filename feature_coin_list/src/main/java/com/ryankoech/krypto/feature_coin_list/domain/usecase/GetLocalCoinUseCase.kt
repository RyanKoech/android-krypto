package com.ryankoech.krypto.feature_coin_list.domain.usecase

import com.ryankoech.krypto.common.core.util.Resource
import com.ryankoech.krypto.feature_coin_list.core.di.HILT_NAME_REPO_FOR_ALL
import com.ryankoech.krypto.feature_coin_list.data.dto.CoinLocalDto
import com.ryankoech.krypto.feature_coin_list.domain.repository.CoinRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject
import javax.inject.Named

class GetLocalCoinUseCase @Inject constructor(
    @Named(HILT_NAME_REPO_FOR_ALL) private val repository: CoinRepository
){
    operator fun invoke(coinId : String) = flow<Resource<CoinLocalDto>> {

        repository.getCoin(coinId)?.apply {
            emit(Resource.Success(this))
            return@flow
        }
        throw Exception("No such coin found")
    }.onStart {
        emit(Resource.Loading())
    }.catch { e ->
        emit(Resource.Error(e.localizedMessage ?: "An Unknown Error Occurred"))
    }

}
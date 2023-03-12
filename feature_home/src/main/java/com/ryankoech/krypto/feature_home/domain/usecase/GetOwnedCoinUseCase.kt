package com.ryankoech.krypto.feature_home.domain.usecase

import com.ryankoech.krypto.common.core.ktx.isNull
import com.ryankoech.krypto.common.core.util.Resource
import com.ryankoech.krypto.feature_coin_list.core.di.HILT_NAME_REPO_FOR_ALL as HILT_NAME_REPO_FOR_ALL_COIN_LIST
import com.ryankoech.krypto.feature_coin_list.domain.repository.CoinRepository
import com.ryankoech.krypto.feature_home.core.di.HILT_NAME_REPO_FOR_ALL as HILT_NAME_REPO_FOR_ALL_HOME
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
    @Named(HILT_NAME_REPO_FOR_ALL_HOME) private val repository: OwnedCoinsRepository,
    @Named(HILT_NAME_REPO_FOR_ALL_COIN_LIST) private val coinRepository: CoinRepository,
) {

    operator fun invoke(coinId : String) = flow<Resource<OwnedCoinDto>> {
        val ownedCoin = repository.getOwnedCoin(coinId)
        if(ownedCoin.isNull()){
            val coinLocal = coinRepository.getCoin(coinId)

            if(coinLocal.isNull()){
                throw Exception("No Corresponding Local Coin Found.")
            }
            val ownedCoinFromLocalCoin = OwnedCoinDto(
                id = coinLocal!!.id,
                symbol = coinLocal.symbol,
                value = coinLocal.price,
                change = coinLocal.change,
                amount = 0.0,
                icon = coinLocal.image
            )
            emit(Resource.Success(ownedCoinFromLocalCoin))
            return@flow
        }

        emit(Resource.Success(ownedCoin!!))
    }.onStart {
        emit(Resource.Loading())
    }.catch { e ->
        Timber.e(e)
        emit(Resource.Error(e.localizedMessage ?: "An Unknown Error Occured"))
    }.flowOn(Dispatchers.IO)

}
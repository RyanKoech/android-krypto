package com.ryankoech.krypto.feature_transaction.domain.usecase


import com.ryankoech.krypto.common.core.util.Resource
import com.ryankoech.krypto.feature_coin_list.core.di.HILT_NAME_REPO_FOR_ALL
import com.ryankoech.krypto.feature_coin_list.data.dto.toLocalCoinDto
import com.ryankoech.krypto.feature_coin_list.domain.repository.CoinRepository
import com.ryankoech.krypto.feature_transaction.domain.entity.Coin
import com.ryankoech.krypto.feature_transaction.domain.entity.toCoinEntity
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named

class GetCoinListUseCase @Inject constructor(
    @Named(HILT_NAME_REPO_FOR_ALL) private val repository: CoinRepository,
) {

    private var cache : List<Coin>? = null

    operator fun invoke(filterString : String = "") = flow<Resource<List<Coin>>> {
        val coins : List<Coin> = if(cache.isNullOrEmpty()) {
            val localCoins = repository.getLocalCoins().toCoinEntity()
            cache = localCoins
            localCoins
        } else {
            cache!!
        }
        if(coins.isEmpty()) {

            val response = repository.getCoins()

            if(response.isSuccessful && !response.body().isNullOrEmpty()){
                val responseCoins = response.body()!!
                repository.saveCoins(responseCoins.toLocalCoinDto())
                val processedCoins = response.body()!!.toCoinEntity()
                cache = processedCoins

                emit(Resource.Success(processedCoins))
            }else{
                throw Exception("Response not Successful.")
            }

        }else {
            emit(Resource.Success(
                coins.filter {
                    it.name.contains(filterString,true)
                }
            ))
        }

    }.onStart {
        emit(Resource.Loading())
    }.catch { e ->
        Timber.e(e)
        emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
    }

}
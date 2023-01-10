package com.ryankoech.krypto.feature_coin_list.domain.usecase

import com.ryankoech.krypto.common.core.util.Resource
import com.ryankoech.krypto.feature_coin_list.core.di.HILT_NAME_REPO_FOR_ALL
import com.ryankoech.krypto.feature_coin_list.data.dto.CoinDto
import com.ryankoech.krypto.feature_coin_list.data.dto.toCoinEntity
import com.ryankoech.krypto.feature_coin_list.domain.entity.Coin
import com.ryankoech.krypto.feature_coin_list.domain.entity.Order
import com.ryankoech.krypto.feature_coin_list.domain.entity.SortCoinBy
import com.ryankoech.krypto.feature_coin_list.domain.entity.SortInfo
import com.ryankoech.krypto.feature_coin_list.domain.repository.CoinRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named

class GetCoinsUseCase @Inject constructor(
    @Named(HILT_NAME_REPO_FOR_ALL) private val repository: CoinRepository
) {

    // Basic Cache
    var cacheCoin : List<CoinDto>? = null

    operator fun invoke(sortInfo: SortInfo, filterString : String = "") = flow {

        val response : Response<List<CoinDto>> = if(cacheCoin.isNullOrEmpty()) {
            val response = repository.getCoins()
            cacheCoin = response.body()
            response
        } else {
            Response.success(cacheCoin)
        }

        if(response.isSuccessful && !response.body().isNullOrEmpty()){
            val filteredCoins =  response.body()!!.toCoinEntity().filter {
                it.name.contains(
                    filterString,
                    true
                ) || it.symbol.contains(
                    filterString,
                    true
                )
            }
            val sortedData = sortData(sortInfo, filteredCoins)
            emit(Resource.Success(data = sortedData))
        }else{
            emit(Resource.Error("Response not Successful."))
        }
    }.onStart {
        emit(Resource.Loading())
    }.catch { e ->
        Timber.e(e)
        emit(Resource.Error(e.localizedMessage ?: "Unexpected Error Occurred."))
    }

    private fun sortData(sortInfo: SortInfo, unsortedData : List<Coin>) : List<Coin> {
        return when(sortInfo.sortBy){
            SortCoinBy.MARKET_CAP -> {
                when(sortInfo.order){
                    Order.ASC -> {
                        unsortedData.sortedBy { it.marketCap }
                    }
                    Order.DESC -> {
                        unsortedData.sortedByDescending { it.marketCap }
                    }
                }
            }
            SortCoinBy.TOTAL_VOLUME -> {
                when(sortInfo.order){
                    Order.ASC -> {
                        unsortedData.sortedBy { it.totalVolume }
                    }
                    Order.DESC -> {
                        unsortedData.sortedByDescending { it.totalVolume }
                    }
                }
            }
            SortCoinBy.PRICE -> {
                when(sortInfo.order){
                    Order.ASC -> {
                        unsortedData.sortedBy { it.price }
                    }
                    Order.DESC -> {
                        unsortedData.sortedByDescending { it.price}
                    }
                }
            }
        }
    }
}
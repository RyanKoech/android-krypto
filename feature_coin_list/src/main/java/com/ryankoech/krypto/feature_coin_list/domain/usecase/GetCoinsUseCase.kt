package com.ryankoech.krypto.feature_coin_list.domain.usecase

import com.ryankoech.krypto.common.core.util.Resource
import com.ryankoech.krypto.common.presentation.util.DisplayCurrency
import com.ryankoech.krypto.feature_coin_list.core.di.HILT_NAME_REPO_FOR_ALL
import com.ryankoech.krypto.feature_coin_list.data.dto.CoinDto
import com.ryankoech.krypto.feature_coin_list.data.dto.toCoinEntity
import com.ryankoech.krypto.feature_coin_list.data.dto.toLocalCoinDto
import com.ryankoech.krypto.feature_coin_list.domain.entity.Coin
import com.ryankoech.krypto.feature_coin_list.domain.entity.Order
import com.ryankoech.krypto.feature_coin_list.domain.entity.SortCoinBy
import com.ryankoech.krypto.feature_coin_list.domain.entity.SortInfo
import com.ryankoech.krypto.feature_coin_list.domain.repository.CoinRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import org.jetbrains.annotations.VisibleForTesting
import retrofit2.Response
import timber.log.Timber
import java.util.*
import javax.inject.Inject
import javax.inject.Named

class GetCoinsUseCase @Inject constructor(
    @Named(HILT_NAME_REPO_FOR_ALL) private val repository: CoinRepository
) {

    // Basic Cache
    private var cacheCoin : List<CoinDto>? = null

    operator fun invoke(sortInfo: SortInfo, filterString : String = "") = flow<Resource<List<Coin>>> {

        val response : Response<List<CoinDto>> = if(cacheCoin.isNullOrEmpty()) {
            val response = repository.getCoins()
            cacheCoin = response.body()

            // Clear cache after 1 minute
            Timer().schedule(object : TimerTask() {
                override fun run() {
                    cacheCoin = null
                }
            }, 60_000)

            // Save to local
            if(!cacheCoin.isNullOrEmpty()) {
                repository.saveCoins(cacheCoin!!.toLocalCoinDto())
                saveDisplayCurrencies(cacheCoin!!)
            }
            response
        } else {
            Response.success(cacheCoin)
        }

        if(response.isSuccessful && !response.body().isNullOrEmpty()){
            val processedCoins = response.body()!!.toCoinEntity().processCoins(filterString, sortInfo)
            emit(Resource.Success(processedCoins))
        }else{
            throw Exception("Response not Successful.")
        }
    }.onStart {
        emit(Resource.Loading())
    }.catch { e ->
        Timber.e(e)
        try{
            val coins = repository.getLocalCoins().toCoinEntity()
            if(coins.isEmpty()){
                emit(Resource.Error(e.localizedMessage ?: "Unexpected Error Occurred."))
            }else {
                val processedCoins = coins.processCoins(filterString, sortInfo)
                emit(Resource.Success(processedCoins))
            }
        }catch(e : Throwable) {
            Timber.e(e)
            emit(Resource.Error(e.localizedMessage ?: "Unexpected Error Occurred."))
        }
    }

    private suspend fun saveDisplayCurrencies(coins : List<CoinDto>) {
        val displayCurrenciesMap = hashMapOf<DisplayCurrency, Double>()
        displayCurrenciesMap[DisplayCurrency.USD] = 1.0
        val displayCurrencies = DisplayCurrency.values().map { it.toString() }
        for (coin in coins) {
            val coinSymbol = coin.symbol.uppercase(Locale.ROOT)
            if (displayCurrencies.contains(coinSymbol)) {
                displayCurrenciesMap[DisplayCurrency.valueOf(coinSymbol)] = coin.current_price
            }
        }

        repository.saveDisplayCurrencyData(displayCurrenciesMap)
    }
    companion object{
        @VisibleForTesting
        fun List<Coin>.sortData(sortInfo: SortInfo): List<Coin> {
            return when (sortInfo.sortBy) {
                SortCoinBy.MARKET_CAP -> {
                    when (sortInfo.order) {
                        Order.ASC -> {
                            sortedBy { it.marketCap }
                        }
                        Order.DESC -> {
                            sortedByDescending { it.marketCap }
                        }
                    }
                }
                SortCoinBy.TOTAL_VOLUME -> {
                    when (sortInfo.order) {
                        Order.ASC -> {
                            sortedBy { it.totalVolume }
                        }
                        Order.DESC -> {
                            sortedByDescending { it.totalVolume }
                        }
                    }
                }
                SortCoinBy.PRICE -> {
                    when (sortInfo.order) {
                        Order.ASC -> {
                            sortedBy { it.price }
                        }
                        Order.DESC -> {
                            sortedByDescending { it.price }
                        }
                    }
                }
            }
        }

        @VisibleForTesting
        fun List<Coin>.processCoins(filterString: String, sortInfo: SortInfo): List<Coin> =
            filter {
                it.name.contains(
                    filterString,
                    true
                ) || it.symbol.contains(
                    filterString,
                    true
                )
            }.sortData(sortInfo)
    }

}
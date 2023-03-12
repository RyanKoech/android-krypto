package com.ryankoech.krypto.feature_coin_details.domain.usecase

import com.ryankoech.krypto.common.core.ktx.isNotNull
import com.ryankoech.krypto.common.core.util.Resource
import com.ryankoech.krypto.feature_coin_details.core.di.HILT_NAME_REPO_FOR_ALL
import com.ryankoech.krypto.feature_coin_details.data.dto.market_chart_dto.toMarketChartEntityList
import com.ryankoech.krypto.feature_coin_details.domain.entity.MarketChartEntity
import com.ryankoech.krypto.feature_coin_details.domain.repository.CoinDetailsRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import timber.log.Timber
import java.util.*
import javax.inject.Inject
import javax.inject.Named

class GetCoinMarketChartUseCase @Inject constructor(
    @Named(HILT_NAME_REPO_FOR_ALL) private val repository: CoinDetailsRepository
) {
    private var cacheCoinId : String? = null
    private var cacheChartEntities : List<List<MarketChartEntity>>? = null

    operator fun invoke(coinId : String) = flow<Resource<List<List<MarketChartEntity>>>> {

        if(cacheCoinId.isNotNull() && cacheCoinId == coinId && !cacheChartEntities.isNullOrEmpty()){
            emit(Resource.Success(
                    cacheChartEntities!!
            ))
            return@flow
        }

        val dayMarketChartResponse = repository.getDayMarketChart(coinId)
        val threeMonthMarketChartResponse = repository.getThreeMonthsMarketChart(coinId)
        val yearMarketChartResponse = repository.getYearMarketChart(coinId)

        if(dayMarketChartResponse.isSuccessful && threeMonthMarketChartResponse.isSuccessful && yearMarketChartResponse.isSuccessful) {

            val dayMarketChartEntity = dayMarketChartResponse.body()!!.toMarketChartEntityList()
            val threeMonthMarketChartEntity = threeMonthMarketChartResponse.body()!!.toMarketChartEntityList()
            val yearMarketChartEntity = yearMarketChartResponse.body()!!.toMarketChartEntityList()

            // Clear cache after 1 minute
            Timer().schedule(object : TimerTask() {
                override fun run() {
                    cacheChartEntities = null
                    cacheCoinId = null
                }
            }, 60_000)

            cacheChartEntities = listOf(
                dayMarketChartEntity,
                threeMonthMarketChartEntity,
                yearMarketChartEntity
            )
            cacheCoinId = coinId

            emit(Resource.Success(
                cacheChartEntities!!
            ))
        } else {
            throw Exception("Response not Successful.")
        }

    }.onStart {
        emit(Resource.Loading())
    }.catch { e ->
        Timber.e(e)
        emit(Resource.Error( e.localizedMessage ?: "Unexpected Error Occurred."))
    }

}
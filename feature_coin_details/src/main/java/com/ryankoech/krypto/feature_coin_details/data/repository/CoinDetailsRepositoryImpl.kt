package com.ryankoech.krypto.feature_coin_details.data.repository

import com.ryankoech.krypto.feature_coin_details.data.data_source.remote.CoinServiceApi
import com.ryankoech.krypto.feature_coin_details.data.dto.market_chart_dto.MarketChartDto
import com.ryankoech.krypto.feature_coin_details.domain.repository.CoinDetailsRepository
import kotlinx.coroutines.coroutineScope
import retrofit2.Response
import javax.inject.Inject

class CoinDetailsRepositoryImpl @Inject constructor(
    private val apiService : CoinServiceApi
) : CoinDetailsRepository {

    override suspend fun getDayMarketChart(coinId : String): Response<MarketChartDto> {
        return coroutineScope {
            apiService.getCoinMarketChart(coinId, 1)
        }
    }

    override suspend fun getThreeMonthsMarketChart(coinId : String): Response<MarketChartDto> {
        return coroutineScope {
            apiService.getCoinMarketChart(coinId, 90)
        }
    }

    override suspend fun getYearMarketChart(coinId : String): Response<MarketChartDto> {
        return coroutineScope {
            apiService.getCoinMarketChart(coinId, 365)
        }
    }

}
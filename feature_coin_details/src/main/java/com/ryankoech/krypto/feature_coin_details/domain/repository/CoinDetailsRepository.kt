package com.ryankoech.krypto.feature_coin_details.domain.repository

import com.ryankoech.krypto.feature_coin_details.data.dto.market_chart_dto.MarketChartDto
import com.ryankoech.krypto.feature_coin_details.domain.entity.MarketChartEntity
import retrofit2.Response

interface CoinDetailsRepository {

    suspend fun getDayMarketChart(coinId : String) : Response<MarketChartDto>

    suspend fun getThreeMonthsMarketChart(coinId : String) : Response<MarketChartDto>

    suspend fun getYearMarketChart(coinId : String) : Response<MarketChartDto>

}
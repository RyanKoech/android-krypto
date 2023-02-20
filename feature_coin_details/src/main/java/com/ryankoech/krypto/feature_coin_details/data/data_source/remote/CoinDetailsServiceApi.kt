package com.ryankoech.krypto.feature_coin_details.data.data_source.remote

import com.ryankoech.krypto.feature_coin_details.data.dto.market_chart_dto.MarketChartDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoinDetailsServiceApi {

    @GET("/coins/{id}/market_chart")
    suspend fun getCoinMarketChart(
        @Path(value = "id", encoded = true) coinId : String,
        @Query("days") days : Int,
        @Query("vs_currency") currency : String = "usd",
    ) : Response<MarketChartDto>

}
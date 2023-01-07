package com.ryankoech.krypto.feature_coin_list.data.data_source.remote

import com.ryankoech.krypto.feature_coin_list.data.dto.CoinDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinServiceApi {

    @GET("coins/markets")
    suspend fun getCoins(
        @Query("vs_currency") currency : String = "usd",
        @Query("order") order : String = "market_cap_desc",
        @Query("per_page") perPage : Int = 500,
        @Query("page") page : Int = 1,
        @Query("sparkline") sparkline : Boolean = false
    ) : Response<List<CoinDto>>

}
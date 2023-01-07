package com.ryankoech.krypto.feature_coin_list.domain.repository

import com.ryankoech.krypto.feature_coin_list.data.dto.CoinDto
import retrofit2.Response

interface CoinRepository {

    suspend fun getCoins() : Response<List<CoinDto>>

}
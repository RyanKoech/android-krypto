package com.ryankoech.krypto.feature_coin_list.domain.repository

import com.ryankoech.krypto.feature_coin_list.data.dto.CoinDto
import com.ryankoech.krypto.feature_coin_list.data.dto.CoinLocalDto
import retrofit2.Response

interface CoinRepository {

    suspend fun getCoins() : Response<List<CoinDto>>

    suspend fun getLocalCoins() : List<CoinLocalDto>

    suspend fun saveCoins(coins : List<CoinLocalDto>)
}
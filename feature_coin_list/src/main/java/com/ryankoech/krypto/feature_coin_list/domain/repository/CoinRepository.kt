package com.ryankoech.krypto.feature_coin_list.domain.repository

import com.ryankoech.krypto.common.presentation.util.DisplayCurrency
import com.ryankoech.krypto.feature_coin_list.data.dto.CoinDto
import com.ryankoech.krypto.feature_coin_list.data.dto.CoinLocalDto
import com.ryankoech.krypto.feature_coin_list.data.dto.display_currency.DisplayCurrencyDto
import retrofit2.Response

interface CoinRepository {

    suspend fun getCoins() : Response<List<CoinDto>>

    suspend fun getLocalCoins() : List<CoinLocalDto>

    suspend fun saveCoins(coins : List<CoinLocalDto>)

    suspend fun getCoin(coinId : String) : CoinLocalDto?

    suspend fun saveDisplayCurrencyData(displayCurrencyDataMap : HashMap<DisplayCurrency, Double>)

    suspend fun getDisplayCurrencyData() : List<DisplayCurrencyDto>?
}
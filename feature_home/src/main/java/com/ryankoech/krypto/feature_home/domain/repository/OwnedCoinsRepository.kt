package com.ryankoech.krypto.feature_home.domain.repository

import com.ryankoech.krypto.common.presentation.util.DisplayCurrency
import com.ryankoech.krypto.feature_home.data.dto.display_currency.DisplayCurrencyDto
import com.ryankoech.krypto.feature_home.data.dto.owned_coin.OwnedCoinDto

interface OwnedCoinsRepository {

    suspend fun saveOwnedCoin(coin : OwnedCoinDto) : Long

    suspend fun getOwnedCoins() : List<OwnedCoinDto>

    suspend fun saveDisplayCurrencyData(displayCurrencyDataMap : HashMap<DisplayCurrency, Double>)

    suspend fun getDisplayCurrencyData() : List<DisplayCurrencyDto>?

}
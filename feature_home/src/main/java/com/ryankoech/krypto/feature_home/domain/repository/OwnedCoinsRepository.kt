package com.ryankoech.krypto.feature_home.domain.repository

import com.ryankoech.krypto.feature_home.data.dto.owned_coin.OwnedCoinDto

interface OwnedCoinsRepository {

    suspend fun saveOwnedCoin(coin : OwnedCoinDto) : String

    suspend fun getOwnedCoin(coinId : String) : OwnedCoinDto?

    suspend fun getOwnedCoins() : List<OwnedCoinDto>

    suspend fun wipeDatabase()

    suspend fun deleteOwnedCoin(coinId : String)
}
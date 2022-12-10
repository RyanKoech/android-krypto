package com.ryankoech.krypto.feature_home.domain.repository

import com.ryankoech.krypto.feature_home.data.dto.owned_coin.OwnedCoinDto

interface OwnedCoinsRepository {

    suspend fun saveOwnedCoin(coin : OwnedCoinDto) : Long

    suspend fun getOwnedCoins() : List<OwnedCoinDto>

}
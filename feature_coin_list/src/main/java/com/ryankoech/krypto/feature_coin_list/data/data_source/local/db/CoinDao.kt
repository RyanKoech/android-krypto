package com.ryankoech.krypto.feature_coin_list.data.data_source.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ryankoech.krypto.feature_coin_list.data.dto.CoinLocalDto
import com.ryankoech.krypto.feature_coin_list.data.dto.TANLENAME_COIN_DTO

@Dao
interface CoinDao {

    @Insert
    suspend fun insertCoins(coins : List<CoinLocalDto>)

    @Query("SELECT * FROM $TANLENAME_COIN_DTO")
    suspend fun getCoins() : List<CoinLocalDto>

    @Query("SELECT * FROM $TANLENAME_COIN_DTO WHERE id = :coinId")
    suspend fun getCoin(coinId : String) : CoinLocalDto?

}
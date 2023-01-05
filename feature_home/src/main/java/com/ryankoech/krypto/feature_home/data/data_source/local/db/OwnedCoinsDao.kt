package com.ryankoech.krypto.feature_home.data.data_source.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ryankoech.krypto.feature_home.data.dto.owned_coin.OWNED_COIN_DTO_TABLENAME
import com.ryankoech.krypto.feature_home.data.dto.owned_coin.OwnedCoinDto

@Dao
interface OwnedCoinsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewCoin(coin : OwnedCoinDto)

    @Query("SELECT * FROM $OWNED_COIN_DTO_TABLENAME")
    suspend fun getOwnedCoins() : List<OwnedCoinDto>
}
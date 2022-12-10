package com.ryankoech.krypto.feature_home.data.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ryankoech.krypto.feature_home.data.dto.owned_coin.OwnedCoinDto

const val DB_NAME ="owned_coin_db"

@Database(
    entities =[OwnedCoinDto::class],
    version = 1,
    exportSchema = false
)
abstract class OwnedCoinsDatabase : RoomDatabase() {
    abstract fun ownedCoinDao() : OwnedCoinsDao
}
package com.ryankoech.krypto.feature_coin_list.data.data_source.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ryankoech.krypto.feature_coin_list.data.dto.CoinLocalDto

const val DB_NAME = "coin_db"

@Database(
    entities = [CoinLocalDto::class],
    version = 1,
    exportSchema = false
)
abstract class CoinDatabase : RoomDatabase() {

    abstract fun getCoinDao() : CoinDao

}
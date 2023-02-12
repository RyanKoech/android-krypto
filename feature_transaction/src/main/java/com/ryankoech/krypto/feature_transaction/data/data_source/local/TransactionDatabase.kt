package com.ryankoech.krypto.feature_transaction.data.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase

const val NAME_DATABASE_TRANSACTION = "transaction db"

@Database(
    entities = [TransactionDao::class],
    version = 1,
    exportSchema = false,
)
abstract class TransactionDatabase : RoomDatabase() {

    abstract fun getTransactionDao() : TransactionDao

}
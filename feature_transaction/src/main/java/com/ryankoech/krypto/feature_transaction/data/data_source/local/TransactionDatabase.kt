package com.ryankoech.krypto.feature_transaction.data.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ryankoech.krypto.feature_transaction.data.dto.transaction_dto.TransactionDto

const val NAME_DATABASE_TRANSACTION = "transaction db"

@Database(
    entities = [TransactionDto::class],
    version = 1,
    exportSchema = false,
)
abstract class TransactionDatabase : RoomDatabase() {

    abstract fun getTransactionDao() : TransactionDao

}
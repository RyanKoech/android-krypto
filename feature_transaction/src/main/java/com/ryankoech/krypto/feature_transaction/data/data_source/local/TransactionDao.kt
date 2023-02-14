package com.ryankoech.krypto.feature_transaction.data.data_source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ryankoech.krypto.feature_transaction.data.dto.transaction_dto.TABLE_NAME_TRANSACTION
import com.ryankoech.krypto.feature_transaction.data.dto.transaction_dto.TransactionDto

@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transactionDto: TransactionDto)

    @Query("SELECT * FROM `$TABLE_NAME_TRANSACTION` WHERE coinId = :coinId ORDER by date DESC")
    suspend fun getCoinTransactions(coinId : String) : List<TransactionDto>

}
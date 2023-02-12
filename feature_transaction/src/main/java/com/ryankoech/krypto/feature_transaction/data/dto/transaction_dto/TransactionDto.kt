package com.ryankoech.krypto.feature_transaction.data.dto.transaction_dto

import androidx.room.Entity
import androidx.room.PrimaryKey

const val TABLE_NAME_TRANSACTION = "table name transaction"

@Entity(tableName = TABLE_NAME_TRANSACTION)
data class TransactionDto(
    @PrimaryKey
    val date : Long,
    val coinId : String,
    val transactionType : TransactionType,
    val currentPrice : Double,
    val amount : Float,
)

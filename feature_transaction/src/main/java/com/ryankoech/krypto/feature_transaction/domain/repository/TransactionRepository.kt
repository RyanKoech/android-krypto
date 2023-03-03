package com.ryankoech.krypto.feature_transaction.domain.repository

import com.ryankoech.krypto.feature_transaction.data.dto.transaction_dto.TransactionDto

interface TransactionRepository {

    suspend fun addTransaction(transaction : TransactionDto)

    suspend fun getCoinTransactions(coinId : String) : List<TransactionDto>

    suspend fun deleteCoinTransaction(date : Long)
}
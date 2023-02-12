package com.ryankoech.krypto.feature_transaction.domain.repository

import com.ryankoech.krypto.feature_transaction.data.dto.transaction_dto.TransactionDto

interface TransactionRepository {

    fun addTransaction(transaction : TransactionDto)

    fun getCoinTransactions(coinId : String) : List<TransactionDto>
}
package com.ryankoech.krypto.feature_transaction.data.repository

import com.ryankoech.krypto.feature_transaction.data.data_source.local.TransactionDao
import com.ryankoech.krypto.feature_transaction.data.dto.transaction_dto.TransactionDto
import com.ryankoech.krypto.feature_transaction.domain.repository.TransactionRepository
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val dao: TransactionDao
) : TransactionRepository {

    override fun addTransaction(transaction: TransactionDto) = dao.insertTransaction(transaction)

    override fun getCoinTransactions(coinId: String): List<TransactionDto> = dao.getCoinTransactions(coinId)

}
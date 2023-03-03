package com.ryankoech.krypto.feature_transaction.data.repository

import com.ryankoech.krypto.feature_transaction.data.data_source.local.TransactionDao
import com.ryankoech.krypto.feature_transaction.data.dto.transaction_dto.TransactionDto
import com.ryankoech.krypto.feature_transaction.domain.repository.TransactionRepository
import kotlinx.coroutines.coroutineScope
import timber.log.Timber
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val dao: TransactionDao
) : TransactionRepository {

    override suspend fun addTransaction(transaction: TransactionDto) {
        return coroutineScope {
            dao.insertTransaction(transaction)
        }
    }

    override suspend fun getCoinTransactions(coinId: String): List<TransactionDto> = dao.getCoinTransactions(coinId)

    override suspend fun deleteCoinTransaction(date: Long) {
        return coroutineScope {
            dao.deleteTransaction(date)
        }
    }

}
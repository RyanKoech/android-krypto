package com.ryankoech.krypto.feature_transaction.data.repository

import com.ryankoech.krypto.feature_transaction.data.dto.transaction_dto.TransactionDto
import com.ryankoech.krypto.feature_transaction.data.dto.transaction_dto.TransactionType
import com.ryankoech.krypto.feature_transaction.domain.repository.TransactionRepository
import timber.log.Timber
import javax.inject.Inject

val bitCoinTransaction = listOf(
    TransactionDto(
        date = 1675655596952,
        coinId = "bitcoin",
        currentPrice = 20000.00,
        amount = 1.5,
        transactionType = TransactionType.BUY
    ),
    TransactionDto(
        date = 1675655696952,
        coinId = "bitcoin",
        currentPrice = 24000.00,
        amount = 0.5,
        transactionType = TransactionType.SELL
    ),
)

class FakeTransactionRepository @Inject constructor() : TransactionRepository {
    override suspend fun addTransaction(transaction: TransactionDto) {
        Timber.d("Added transaction.")
        Timber.d(transaction.toString())
    }

    override suspend fun getCoinTransactions(coinId: String): List<TransactionDto> {
        return if(coinId == bitCoinTransaction.first().coinId){
            bitCoinTransaction
        } else {
            listOf()
        }

    }

    override suspend fun deleteCoinTransaction(date: Long) {
        Timber.d("Deleted transaction: $date")
    }
}
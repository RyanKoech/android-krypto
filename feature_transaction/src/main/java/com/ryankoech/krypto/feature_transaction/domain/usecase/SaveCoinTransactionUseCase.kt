package com.ryankoech.krypto.feature_transaction.domain.usecase

import com.ryankoech.krypto.common.core.util.Resource
import com.ryankoech.krypto.feature_transaction.core.di.HILT_NAME_REPO_FOR_ALL
import com.ryankoech.krypto.feature_transaction.data.dto.transaction_dto.TransactionDto
import com.ryankoech.krypto.feature_transaction.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject
import javax.inject.Named

class SaveCoinTransactionUseCase @Inject constructor(
    @Named(HILT_NAME_REPO_FOR_ALL) private val repository: TransactionRepository
) {

    operator fun invoke(transaction: TransactionDto) = flow<Resource<Unit>> {
        emit(Resource.Success(repository.addTransaction(transaction)))
    }.onStart {
        emit(Resource.Loading())
    }.catch { e ->
        emit(Resource.Error(e.localizedMessage ?: "An unknown error occurred" ))
    }

}
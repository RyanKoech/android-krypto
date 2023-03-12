package com.ryankoech.krypto.feature_transaction.domain.usecase

import com.ryankoech.krypto.common.core.util.Resource
import com.ryankoech.krypto.feature_coin_list.core.di.HILT_NAME_REPO_FOR_ALL
import com.ryankoech.krypto.feature_transaction.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject
import javax.inject.Named

class DeleteCoinTransactionUseCase @Inject constructor(
    @Named(HILT_NAME_REPO_FOR_ALL)  private val repository: TransactionRepository
) {
    operator fun invoke(date : Long) = flow<Resource<Unit>> {
        emit(
            Resource.Success(repository.deleteCoinTransaction(date))
        )
    }.onStart {
        emit(Resource.Loading())
    }.catch { e ->
        emit(Resource.Error(e.localizedMessage ?: "An Unknown Error Occurred"))
    }

}
package com.ryankoech.krypto.feature_transaction.core.di

import com.ryankoech.krypto.feature_transaction.data.repository.FakeTransactionRepository
import com.ryankoech.krypto.feature_transaction.data.repository.TransactionRepositoryImpl
import com.ryankoech.krypto.feature_transaction.domain.repository.TransactionRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

const val HILT_NAMED_REPO_FAKE = "FakeRepo"
const val HILT_NAMED_REPO = "Repo"
const val HILT_NAME_REPO_FOR_ALL = HILT_NAMED_REPO

@Module
@InstallIn(SingletonComponent::class)
abstract class TransactionRepositoryModule {

    @Binds
    @Singleton
    @Named(HILT_NAMED_REPO)
    abstract fun getTrasnactionRepository(
        repository: TransactionRepositoryImpl
    ) : TransactionRepository

    @Binds
    @Singleton
    @Named(HILT_NAMED_REPO_FAKE)
    abstract fun getFakeTransactionRepository(
        repository : FakeTransactionRepository
    ) : TransactionRepository

}
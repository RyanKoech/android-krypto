package com.ryankoech.krypto.feature_transaction.core.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ryankoech.krypto.feature_transaction.data.data_source.local.NAME_DATABASE_TRANSACTION
import com.ryankoech.krypto.feature_transaction.data.data_source.local.TransactionDao
import com.ryankoech.krypto.feature_transaction.data.data_source.local.TransactionDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TransactionDatabaseModule {

    @Provides
    @Singleton
    fun getTransactionDatabase(@ApplicationContext context : Context) : TransactionDatabase =
        Room.databaseBuilder(
            context,
            TransactionDatabase::class.java,
            NAME_DATABASE_TRANSACTION,
        )
            .fallbackToDestructiveMigration()
            .build()


    @Provides
    @Singleton
    fun getTransactionDao(transactionDatabase: TransactionDatabase) : TransactionDao = transactionDatabase.getTransactionDao()
}
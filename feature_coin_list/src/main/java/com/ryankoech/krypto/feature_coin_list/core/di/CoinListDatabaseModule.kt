package com.ryankoech.krypto.feature_coin_list.core.di

import android.content.Context
import androidx.room.Room
import com.ryankoech.krypto.feature_coin_list.data.data_source.local.db.CoinDao
import com.ryankoech.krypto.feature_coin_list.data.data_source.local.db.CoinDatabase
import com.ryankoech.krypto.feature_coin_list.data.data_source.local.db.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoinListDatabaseModule {

    @Provides
    @Singleton
    fun getCoinDatabase(@ApplicationContext context: Context) : CoinDatabase {
        return Room.databaseBuilder(
            context,
            CoinDatabase::class.java,
            DB_NAME,
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun getCoinDao(coinDatabase: CoinDatabase) : CoinDao = coinDatabase.getCoinDao()

}
package com.ryankoech.krypto.feature_home.core.di

import android.content.Context
import androidx.room.Room
import com.ryankoech.krypto.feature_home.data.data_source.local.db.DB_NAME
import com.ryankoech.krypto.feature_home.data.data_source.local.db.OwnedCoinsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeDatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context : Context) : OwnedCoinsDatabase {
        return Room.databaseBuilder(
            context,
            OwnedCoinsDatabase::class.java,
            DB_NAME,
        ).fallbackToDestructiveMigration().build()
    }
}
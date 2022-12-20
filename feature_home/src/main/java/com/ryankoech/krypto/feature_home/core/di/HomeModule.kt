package com.ryankoech.krypto.feature_home.core.di

import android.content.Context
import androidx.room.Room
import com.ryankoech.krypto.feature_home.data.data_source.local.db.DB_NAME
import com.ryankoech.krypto.feature_home.data.data_source.local.db.OwnedCoinsDao
import com.ryankoech.krypto.feature_home.data.data_source.local.db.OwnedCoinsDatabase
import com.ryankoech.krypto.feature_home.data.repository.FakeOwnedCoinsReposistoryImpl
import com.ryankoech.krypto.feature_home.domain.repository.OwnedCoinsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context : Context) : OwnedCoinsDatabase {
        return Room.databaseBuilder(
            context,
            OwnedCoinsDatabase::class.java,
            DB_NAME,
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideIncompleteSurveyDa(db : OwnedCoinsDatabase) = db.ownedCoinDao()

    @Provides
    @Singleton
    fun provideProvidedRepository(ownedCoinsDao: OwnedCoinsDao) : OwnedCoinsRepository {
        return FakeOwnedCoinsReposistoryImpl(ownedCoinsDao)
    }

}
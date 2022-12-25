package com.ryankoech.krypto.feature_home.core.di

import com.ryankoech.krypto.feature_home.data.data_source.local.db.OwnedCoinsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeDaoModule {

    @Provides
    @Singleton
    fun provideIncompleteSurveyDao(db : OwnedCoinsDatabase) = db.ownedCoinDao()

}
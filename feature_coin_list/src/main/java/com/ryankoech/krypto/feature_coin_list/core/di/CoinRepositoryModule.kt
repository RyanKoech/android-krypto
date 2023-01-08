package com.ryankoech.krypto.feature_coin_list.core.di

import com.ryankoech.krypto.feature_coin_list.data.repository.CoinRepositoryImpl
import com.ryankoech.krypto.feature_coin_list.data.repository.FakeCoinRepositoryImpl
import com.ryankoech.krypto.feature_coin_list.domain.repository.CoinRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

const val HILT_NAMED_REPO_FAKE = "FakeRepo"
const val HILT_NAMED_REPO = "Repo"
const val HILT_NAME_REPO_FOR_ALL = HILT_NAMED_REPO_FAKE

@Module
@InstallIn(SingletonComponent::class)
abstract class CoinRepositoryModule {

    @Binds
    @Singleton
    @Named(HILT_NAMED_REPO_FAKE)
    abstract fun provideFakeCoinRepository(
        coinRepositoryImpl: FakeCoinRepositoryImpl
    ) : CoinRepository

    @Binds
    @Singleton
    @Named(HILT_NAMED_REPO)
    abstract fun provideCoinRepository(
        coinRepositoryImpl: CoinRepositoryImpl
    ) : CoinRepository

}
package com.ryankoech.krypto.feature_coin_details.core.di

import com.ryankoech.krypto.feature_coin_details.data.repository.CoinDetailsRepositoryImpl
import com.ryankoech.krypto.feature_coin_details.data.repository.FakeCoinDetailsRepository
import com.ryankoech.krypto.feature_coin_details.domain.repository.CoinDetailsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

const val HILT_NAMED_REPO_FAKE = "FakeRepo"
const val HILT_NAMED_REPO = "Repo"
const val HILT_NAME_REPO_FOR_ALL = HILT_NAMED_REPO

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    @Named(HILT_NAMED_REPO)
    abstract fun provideCoinDetailRepository(
        repository: CoinDetailsRepositoryImpl
    ) : CoinDetailsRepository

    @Binds
    @Singleton
    @Named(HILT_NAMED_REPO_FAKE)
    abstract fun provideFakeCoinDetailRepository(
        repository: FakeCoinDetailsRepository
    ) : CoinDetailsRepository

}
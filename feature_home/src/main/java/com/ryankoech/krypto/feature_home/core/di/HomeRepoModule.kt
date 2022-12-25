package com.ryankoech.krypto.feature_home.core.di

import com.ryankoech.krypto.feature_home.data.repository.FakeOwnedCoinsReposistoryImpl
import com.ryankoech.krypto.feature_home.data.repository.OwnedCoinsRepositoryImpl
import com.ryankoech.krypto.feature_home.domain.repository.OwnedCoinsRepository
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
abstract class HomeRepoModule {

    @Binds
    @Singleton
    @Named(HILT_NAMED_REPO)
    abstract fun provideRepository(
        ownedCoinsRepositoryImpl: OwnedCoinsRepositoryImpl
    ) : OwnedCoinsRepository

    @Binds
    @Singleton
    @Named(HILT_NAMED_REPO_FAKE)
    abstract fun provideFakeRepository(
        ownedCoinsRepositoryImpl: FakeOwnedCoinsReposistoryImpl
    ) : OwnedCoinsRepository

}
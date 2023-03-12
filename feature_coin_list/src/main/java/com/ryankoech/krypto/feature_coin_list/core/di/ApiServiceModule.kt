package com.ryankoech.krypto.feature_coin_list.core.di

import com.ryankoech.krypto.feature_coin_list.data.data_source.remote.CoinServiceApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule {

    @Singleton
    @Provides
    fun provideCoinServiceApi(retrofit: Retrofit) : CoinServiceApi =
        retrofit.create(CoinServiceApi::class.java)

}
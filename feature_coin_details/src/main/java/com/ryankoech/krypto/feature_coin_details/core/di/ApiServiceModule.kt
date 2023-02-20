package com.ryankoech.krypto.feature_coin_details.core.di

import com.ryankoech.krypto.feature_coin_details.data.data_source.remote.CoinDetailsServiceApi
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
    fun provideCoinServiceApi(retrofit: Retrofit) : CoinDetailsServiceApi =
        retrofit.create(CoinDetailsServiceApi::class.java)


}
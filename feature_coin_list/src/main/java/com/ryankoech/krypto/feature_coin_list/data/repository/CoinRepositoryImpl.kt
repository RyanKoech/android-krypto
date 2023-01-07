package com.ryankoech.krypto.feature_coin_list.data.repository

import com.ryankoech.krypto.feature_coin_list.data.data_source.remote.CoinServiceApi
import com.ryankoech.krypto.feature_coin_list.data.dto.CoinDto
import com.ryankoech.krypto.feature_coin_list.domain.repository.CoinRepository
import retrofit2.Response
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api : CoinServiceApi
) : CoinRepository {

    override suspend fun getCoins(): Response<List<CoinDto>> = api.getCoins()

}
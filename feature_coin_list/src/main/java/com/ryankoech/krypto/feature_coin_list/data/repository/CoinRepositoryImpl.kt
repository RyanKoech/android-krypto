package com.ryankoech.krypto.feature_coin_list.data.repository

import com.ryankoech.krypto.feature_coin_list.data.data_source.local.db.CoinDao
import com.ryankoech.krypto.feature_coin_list.data.data_source.remote.CoinServiceApi
import com.ryankoech.krypto.feature_coin_list.data.dto.CoinDto
import com.ryankoech.krypto.feature_coin_list.data.dto.CoinLocalDto
import com.ryankoech.krypto.feature_coin_list.domain.repository.CoinRepository
import retrofit2.Response
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api : CoinServiceApi,
    private val dao : CoinDao
) : CoinRepository {

    override suspend fun getCoins(): Response<List<CoinDto>> = api.getCoins()

    override suspend fun getLocalCoins(): List<CoinLocalDto>  = dao.getCoins()

    override suspend fun saveCoins(coins: List<CoinLocalDto>) {
        dao.insertCoins(coins)
    }

    override suspend fun getCoin(coinId: String): CoinLocalDto?  = dao.getCoin(coinId)


}
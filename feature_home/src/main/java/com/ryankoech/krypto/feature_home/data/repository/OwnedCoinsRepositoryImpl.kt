package com.ryankoech.krypto.feature_home.data.repository

import com.ryankoech.krypto.feature_home.data.data_source.local.OwnedCoinsDao
import com.ryankoech.krypto.feature_home.data.dto.owned_coin.OwnedCoinDto
import com.ryankoech.krypto.feature_home.domain.repository.OwnedCoinsRepository
import kotlinx.coroutines.coroutineScope

class OwnedCoinsRepositoryImpl(
    private val coinsDao: OwnedCoinsDao
) : OwnedCoinsRepository {

    override suspend fun saveOwnedCoin(coin: OwnedCoinDto): Long {
        return coroutineScope {
            coinsDao.insertNewCoin(coin)
        }
    }

    override suspend fun getOwnedCoins(): List<OwnedCoinDto> {
        return coroutineScope {
            coinsDao.getOwnedCoins()
        }
    }

}
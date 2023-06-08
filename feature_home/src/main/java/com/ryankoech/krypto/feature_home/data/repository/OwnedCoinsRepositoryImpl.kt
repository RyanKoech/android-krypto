package com.ryankoech.krypto.feature_home.data.repository

import com.ryankoech.krypto.common.presentation.util.DisplayCurrency
import com.ryankoech.krypto.feature_home.data.data_source.local.db.OwnedCoinsDao
import com.ryankoech.krypto.feature_home.data.data_source.local.db.OwnedCoinsDatabase
import com.ryankoech.krypto.feature_home.data.dto.owned_coin.OwnedCoinDto
import com.ryankoech.krypto.feature_home.domain.repository.OwnedCoinsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class OwnedCoinsRepositoryImpl @Inject constructor(
    private val coinsDao: OwnedCoinsDao,
    private val ownedCoinsDatabase : OwnedCoinsDatabase
) : OwnedCoinsRepository {

    override suspend fun saveOwnedCoin(coin: OwnedCoinDto): String {
        return coroutineScope {
            coinsDao.insertNewCoin(coin)
            coin.id
        }
    }

    override suspend fun getOwnedCoin(coinId: String): OwnedCoinDto? {
        return coroutineScope {
            coinsDao.getOwnedCoin(coinId)
        }
    }


    override suspend fun getOwnedCoins(): List<OwnedCoinDto> {
        return coroutineScope {
            coinsDao.getOwnedCoins()
        }
    }

    override suspend fun wipeDatabase() {
        return coroutineScope {
            CoroutineScope(Dispatchers.IO).launch {
                ownedCoinsDatabase.clearAllTables()
            }
        }
    }

    override suspend fun deleteOwnedCoin(coinId: String) {
        return coroutineScope {
            coinsDao.deleteOwnedCoin(coinId)
        }
    }

}
package com.ryankoech.krypto.feature_home.data.repository

import com.ryankoech.krypto.common.presentation.util.DisplayCurrency
import com.ryankoech.krypto.feature_home.data.data_source.local.db.OwnedCoinsDao
import com.ryankoech.krypto.feature_home.data.data_source.local.db.OwnedCoinsDatabase
import com.ryankoech.krypto.feature_home.data.data_source.local.shared_pref.HomeLocalPref
import com.ryankoech.krypto.feature_home.data.dto.display_currency.DisplayCurrencyDto
import com.ryankoech.krypto.feature_home.data.dto.owned_coin.OwnedCoinDto
import com.ryankoech.krypto.feature_home.domain.repository.OwnedCoinsRepository
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class OwnedCoinsRepositoryImpl @Inject constructor(
    private val coinsDao: OwnedCoinsDao,
    private val homeLocalPref: HomeLocalPref,
    private val ownedCoinsDatabase : OwnedCoinsDatabase
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

    override suspend fun saveDisplayCurrencyData(displayCurrencyDataMap: HashMap<DisplayCurrency, Double>) {
        return coroutineScope {
            homeLocalPref.saveDisplayCurrencyData(displayCurrencyDataMap)
        }
    }


    override suspend fun getDisplayCurrencyData(): List<DisplayCurrencyDto>? {
        return coroutineScope {
            homeLocalPref.getDisplayCurrencyData()
        }
    }

    override suspend fun wipeDatabase() {
        return coroutineScope{
            ownedCoinsDatabase.clearAllTables()
        }
    }

}
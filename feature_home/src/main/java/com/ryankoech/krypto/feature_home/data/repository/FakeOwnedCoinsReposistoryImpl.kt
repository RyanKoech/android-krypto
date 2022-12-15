package com.ryankoech.krypto.feature_home.data.repository

import com.ryankoech.krypto.feature_home.data.data_source.local.OwnedCoinsDao
import com.ryankoech.krypto.feature_home.data.dto.owned_coin.OwnedCoinDto
import com.ryankoech.krypto.feature_home.domain.repository.OwnedCoinsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import javax.inject.Inject

private val FakeOwnedCoins = listOf<OwnedCoinDto>(
    OwnedCoinDto(
        id = "bitcoin",
        symbol = "btc",
        amount = 1f,
        value = 17434.25f,
        change = -3.60164,
        icon = "https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1547033579"
    ),
    OwnedCoinDto(
        id = "ethereum",
        symbol = "eth",
        amount = 1f,
        value = 1270.21f,
        change = 1.164,
        icon = "https://assets.coingecko.com/coins/images/279/large/ethereum.png?1595348880"
    ),
    OwnedCoinDto(
        id = "tether",
        symbol = "usdt",
        amount = 1f,
        value = 0.99f,
        change = -0.664,
        icon = "https://assets.coingecko.com/coins/images/325/large/Tether.png?1668148663"
    ),
)

class FakeOwnedCoinsReposistoryImpl @Inject constructor(
    private val coinsDao: OwnedCoinsDao
) : OwnedCoinsRepository {

    override suspend fun saveOwnedCoin(coin: OwnedCoinDto): Long {
        return coroutineScope {
            coinsDao.insertNewCoin(coin)
        }
    }

    override suspend fun getOwnedCoins(): List<OwnedCoinDto> {
        return coroutineScope {
            FakeOwnedCoins
        }
    }

}
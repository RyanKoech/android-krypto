package com.ryankoech.krypto.feature_home.data.repository

import com.ryankoech.krypto.feature_home.data.data_source.local.OwnedCoinsDao
import com.ryankoech.krypto.feature_home.data.dto.owned_coin.OwnedCoinDto
import com.ryankoech.krypto.feature_home.domain.repository.OwnedCoinsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import javax.inject.Inject

val FakeOwnedCoins = listOf(
    OwnedCoinDto(
        id = "bitcoin",
        symbol = "btc",
        amount = 1.0,
        value = 17434.25,
        change = -3.60164f,
        icon = "https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1547033579"
    ),
    OwnedCoinDto(
        id = "ethereum",
        symbol = "eth",
        amount = 1.0,
        value = 1270.21,
        change = 1.164f,
        icon = "https://assets.coingecko.com/coins/images/279/large/ethereum.png?1595348880"
    ),
    OwnedCoinDto(
        id = "tether",
        symbol = "usdt",
        amount = 1.0,
        value = 0.99,
        change = -0.664f,
        icon = "https://assets.coingecko.com/coins/images/325/large/Tether.png?1668148663"
    ),
    OwnedCoinDto(
        id = "usd-coin",
        symbol = "usdc",
        amount = 1000.0,
        value = 1.001,
        change = -0.000152f,
        icon = "https://assets.coingecko.com/coins/images/6319/large/USD_Coin_icon.png?1547042389"
    ),
    OwnedCoinDto(
        id = "binancecoin",
        symbol = "bnb",
        amount = 1.0,
        value = 263.5,
        change = -9.231f,
        icon = "https://assets.coingecko.com/coins/images/825/large/bnb-icon2_2x.png?1644979850"
    ),
    OwnedCoinDto(
        id = "ripple",
        symbol = "xrp",
        amount = 56.0,
        value = 0.382107,
        change = 0.393684f,
        icon = "https://assets.coingecko.com/coins/images/44/large/xrp-symbol-white-128.png?1605778731"
    ),
)


const val FAKE_DELAY = 2000L

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
            withContext(Dispatchers.IO) {
                Thread.sleep(FAKE_DELAY)
                FakeOwnedCoins
            }
        }
    }

}
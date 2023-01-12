package com.ryankoech.krypto.feature_coin_list.data.dto

import com.ryankoech.krypto.feature_coin_list.domain.entity.Coin

data class CoinDto(
    val ath: Double,
    val ath_change_percentage: Double,
    val ath_date: String,
    val atl: Double,
    val atl_change_percentage: Double,
    val atl_date: String,
    val circulating_supply: Double,
    val current_price: Double,
    val fully_diluted_valuation: Long,
    val high_24h: Double,
    val id: String,
    val image: String,
    val last_updated: String,
    val low_24h: Double,
    val market_cap: Long,
    val market_cap_change_24h: Double,
    val market_cap_change_percentage_24h: Double,
    val market_cap_rank: Int,
    val max_supply: Double?,
    val name: String,
    val price_change_24h: Double,
    val price_change_percentage_24h: Double,
    val roi: Roi?,
    val symbol: String,
    val total_supply: Double,
    val total_volume: Double
)

fun List<CoinDto>.toCoinEntity() : List<Coin> =
    map {
        Coin(
            id = it.id,
            name = it.name,
            symbol = it.symbol,
            price = it.current_price,
            marketCapRank = it.market_cap_rank,
            image = it.image,
            change = it.price_change_24h.toFloat(),
            marketCap = it.market_cap,
            allTimeHigh = it.ath,
            high24Hr = it.high_24h,
            totalVolume = it.total_volume
        )
    }

fun List<CoinDto>.toLocalCoinDto() : List<CoinLocalDto> =
    map {
        CoinLocalDto(
            id = it.id,
            name = it.name,
            symbol = it.symbol,
            price = it.current_price,
            marketCapRank = it.market_cap_rank,
            image = it.image,
            change = it.price_change_24h.toFloat(),
            marketCap = it.market_cap,
            allTimeHigh = it.ath,
            high24Hr = it.high_24h,
            totalVolume = it.total_volume
        )
    }
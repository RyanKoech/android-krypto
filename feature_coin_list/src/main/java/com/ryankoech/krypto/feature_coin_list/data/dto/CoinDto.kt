package com.ryankoech.krypto.feature_coin_list.data.dto

import com.google.gson.annotations.SerializedName
import com.ryankoech.krypto.feature_coin_list.domain.entity.Coin

data class CoinDto(
    @SerializedName("ath")
    val ath: Double,
    @SerializedName("ath_change_percentage")
    val ath_change_percentage: Double,
    @SerializedName("ath_date")
    val ath_date: String,
    @SerializedName("atl")
    val atl: Double,
    @SerializedName("atl_change_percentage")
    val atl_change_percentage: Double,
    @SerializedName("atl_date")
    val atl_date: String,
    @SerializedName("circulating_supply")
    val circulating_supply: Double,
    @SerializedName("current_price")
    val current_price: Double,
    @SerializedName("fully_diluted_valuation")
    val fully_diluted_valuation: Long,
    @SerializedName("high_24h")
    val high_24h: Double,
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("last_updated")
    val last_updated: String,
    @SerializedName("low_24h")
    val low_24h: Double,
    @SerializedName("market_cap")
    val market_cap: Long,
    @SerializedName("market_cap_change_24h")
    val market_cap_change_24h: Double,
    @SerializedName("market_cap_change_percentage_24h")
    val market_cap_change_percentage_24h: Double,
    @SerializedName("market_cap_rank")
    val market_cap_rank: Int,
    @SerializedName("max_supply")
    val max_supply: Double?,
    @SerializedName("name")
    val name: String,
    @SerializedName("price_change_24h")
    val price_change_24h: Double,
    @SerializedName("price_change_percentage_24h")
    val price_change_percentage_24h: Double,
    @SerializedName("roi")
    val roi: Roi?,
    @SerializedName("symbol")
    val symbol: String,
    @SerializedName("total_supply")
    val total_supply: Double,
    @SerializedName("total_volume")
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
            change = it.price_change_percentage_24h.toFloat(),
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
            change = it.price_change_percentage_24h.toFloat(),
            marketCap = it.market_cap,
            allTimeHigh = it.ath,
            high24Hr = it.high_24h,
            totalVolume = it.total_volume
        )
    }
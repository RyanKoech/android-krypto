package com.ryankoech.krypto.feature_coin_list.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ryankoech.krypto.feature_coin_list.domain.entity.Coin

const val TANLENAME_COIN_DTO = "table_coin_dto"

@Entity(tableName = TANLENAME_COIN_DTO)
data class CoinLocalDto(
    val id : String,
    val name : String,
    val symbol : String,
    @PrimaryKey(autoGenerate = false)
    val marketCapRank : Int,
    val image : String,
    val change : Float,
    val price : Double,
    val marketCap : Long,
    val allTimeHigh : Double,
    val high24Hr : Double,
    val totalVolume : Double,
)


fun List<CoinLocalDto>.toCoinEntity() : List<Coin> = map {
    Coin(
        id = it.id,
        name = it.name,
        symbol = it.symbol,
        price = it.price,
        marketCapRank = it.marketCapRank,
        image = it.image,
        change = it.change,
        marketCap = it.marketCap,
        allTimeHigh = it.allTimeHigh,
        high24Hr = it.high24Hr,
        totalVolume = it.totalVolume
    )
}
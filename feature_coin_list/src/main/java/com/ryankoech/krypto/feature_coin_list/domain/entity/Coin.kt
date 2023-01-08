package com.ryankoech.krypto.feature_coin_list.domain.entity

data class Coin(
    val id : String,
    val name : String,
    val symbol : String,
    val marketCapRank : Int,
    val image : String,
    val change : Float,
    val price : Double,
    val marketCap : Long,
    val allTimeHigh : Double,
    val high24Hr : Double,
    val totalVolume : Long,
)

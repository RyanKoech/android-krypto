package com.ryankoech.krypto.feature_transaction.domain.entity

import com.ryankoech.krypto.feature_coin_list.data.dto.CoinDto
import com.ryankoech.krypto.feature_coin_list.data.dto.CoinLocalDto

data class Coin (
    val id : String,
    val symbol : String,
    val name : String,
    val image : String,
    val price : Double,
)

fun List<CoinLocalDto>.toCoinEntity() : List<Coin> = map {
    Coin(
        id = it.id,
        name = it.name,
        symbol = it.symbol,
        image = it.image,
        price = it.price,
    )
}

@JvmName("toCoinEntityCoinDto")
fun List<CoinDto>.toCoinEntity() : List<Coin> = map {
    Coin(
        id = it.id,
        name = it.name,
        symbol = it.symbol,
        image = it.image,
        price = it.current_price,
    )
}
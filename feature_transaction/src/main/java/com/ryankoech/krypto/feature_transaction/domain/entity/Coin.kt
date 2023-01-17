package com.ryankoech.krypto.feature_transaction.domain.entity

import com.ryankoech.krypto.feature_coin_list.data.dto.CoinDto
import com.ryankoech.krypto.feature_coin_list.data.dto.CoinLocalDto

data class Coin (
    val name : String,
    val image : String,
)

fun List<CoinLocalDto>.toCoinEntity() : List<Coin> = map {
    Coin(
        name = it.name,
        image = it.image
    )
}

@JvmName("toCoinEntityCoinDto")
fun List<CoinDto>.toCoinEntity() : List<Coin> = map {
    Coin(
        name = it.name,
        image = it.image
    )
}
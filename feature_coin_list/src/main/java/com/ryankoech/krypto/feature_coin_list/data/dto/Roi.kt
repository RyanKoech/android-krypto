package com.ryankoech.krypto.feature_coin_list.data.dto

data class Roi(
    val currency: String,
    val percentage: Double,
    val times: Double
)
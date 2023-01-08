package com.ryankoech.krypto.feature_coin_list.domain.entity

data class SortInfo(
    val sortBy : SortCoinBy,
    val order: Order
)

package com.ryankoech.krypto.feature_coin_details.domain.entity

data class MarketChartEntity(
    val price : Double
)

fun List<MarketChartEntity>.toPairList() : List<Pair<Int, Double>> = mapIndexed{ index, marketEntity ->
    Pair(index, marketEntity.price)
}

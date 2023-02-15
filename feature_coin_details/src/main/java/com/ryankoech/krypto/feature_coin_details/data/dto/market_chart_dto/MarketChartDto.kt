package com.ryankoech.krypto.feature_coin_details.data.dto.market_chart_dto

import com.google.gson.annotations.SerializedName

data class MarketChartDto(
    @SerializedName("market_caps")
    val marketCaps: List<List<Double>>,
    @SerializedName("prices")
    val prices: List<List<Double>>,
    @SerializedName("total_volumes")
    val totalVolumes: List<List<Double>>
)
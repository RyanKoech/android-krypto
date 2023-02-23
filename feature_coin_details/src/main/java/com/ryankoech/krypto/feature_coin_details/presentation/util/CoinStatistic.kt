package com.ryankoech.krypto.feature_coin_details.presentation.util

import androidx.annotation.DrawableRes

data class CoinStatistic(
    val title : String,
    val message : String,
    @DrawableRes val icon : Int,
)

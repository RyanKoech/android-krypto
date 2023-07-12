package com.ryankoech.krypto.feature_coin_list.data.dto.display_currency

import com.google.gson.annotations.SerializedName
import com.ryankoech.krypto.common.presentation.util.DisplayCurrency

data class DisplayCurrencyDto(
    @SerializedName("currency")
    val currency : DisplayCurrency,
    @SerializedName("value")
    val value : Double
)

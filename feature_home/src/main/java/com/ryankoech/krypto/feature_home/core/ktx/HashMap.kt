package com.ryankoech.krypto.feature_home.core.ktx

import com.ryankoech.krypto.common.presentation.util.DisplayCurrency
import com.ryankoech.krypto.feature_home.data.dto.display_currency.DisplayCurrencyDto

fun HashMap<DisplayCurrency, Double>.toDisplayCurrencyList() : List<DisplayCurrencyDto> {

    val displayCurrencyData = mutableListOf<DisplayCurrencyDto>()

    this.forEach{ entry ->
        displayCurrencyData.add(
            DisplayCurrencyDto(
                entry.key,
                entry.value
            )
        )
    }

    return displayCurrencyData.sortedBy { it.currency.ordinal }
}
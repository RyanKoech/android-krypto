package com.ryankoech.krypto.feature_home.presentation.util

import com.ryankoech.krypto.common.presentation.util.DisplayCurrency

data class CreditCardDetails(
    val balance : Double = 0.0,
    val count : Int = 0,
    val change : Float = 0F,
    val displayCurrency : DisplayCurrency
)

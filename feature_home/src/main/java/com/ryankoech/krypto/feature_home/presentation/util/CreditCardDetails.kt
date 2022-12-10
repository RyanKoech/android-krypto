package com.ryankoech.krypto.feature_home.presentation.util

data class CreditCardDetails(
    val balance : Double = 0.0,
    val count : Int = 0,
    val change : Float = 0F,
    val displayCurrency : DisplayCurrency
)

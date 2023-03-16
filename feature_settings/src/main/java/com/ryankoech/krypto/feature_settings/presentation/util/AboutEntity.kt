package com.ryankoech.krypto.feature_settings.presentation.util

data class AboutEntity(
    val title : String,
    val details : String,
    val action : (() -> Unit)? = null,
)

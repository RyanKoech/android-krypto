package com.ryankoech.krypto.feature_settings.presentation.util

import androidx.annotation.DrawableRes

data class SettingsEntity(
    val title : String,
    val details : String,
    @DrawableRes val iconRes : Int,
    val action : () -> Unit,
)

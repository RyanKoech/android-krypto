package com.ryankoech.krypto.feature_transaction.presentation.transaction.util

import androidx.compose.runtime.Composable

data class TabRowItem(
    val title: String,
    val screen: @Composable () -> Unit,
)
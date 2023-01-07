package com.ryankoech.krypto.feature_coin_list.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme

@Composable
fun CoinListScreen() {

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        CoinListScreenSuccess()
    }
}

@Preview
@Composable
fun CoinListScreenPreview() {

    KryptoTheme {
        CoinListScreen()
    }
}
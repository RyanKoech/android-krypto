package com.ryankoech.krypto.feature_coin_list.presentation.components.success

import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme
import com.ryankoech.krypto.common.R
import com.ryankoech.krypto.common.presentation.ErrorScreen

const val TEST_TAG_NO_COINS_FOUND = "test tag no coins found"

@Composable
fun NoCoinsFound(
    modifier: Modifier = Modifier,
) {

    ErrorScreen(
        modifier = modifier,
        messageText = "No coins to show.",
        res = R.drawable.icon_gif_search,
        showButton = false,
    )

}

@Preview
@Composable
fun NoCoinsFoundPreview() {
    KryptoTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            NoCoinsFound()
        }
    }
}
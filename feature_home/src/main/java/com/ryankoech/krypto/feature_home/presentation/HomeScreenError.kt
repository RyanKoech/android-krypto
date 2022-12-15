package com.ryankoech.krypto.feature_home.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ryankoech.krypto.common.presentation.ErrorScreen
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme
import ke.co.sevenskies.feature_home.R

@Composable
fun HomeScreenError(
    modifier : Modifier = Modifier
) {
    ErrorScreen(
        modifier = modifier,
        onButtonClick = {},
        messageText = "Such emptiness",
        buttonText = "Add Your first Coin",
        res = R.drawable.astronaut
    )
}

@Preview
@Composable
fun HomeScreenErrorPreview() {

    KryptoTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {

            HomeScreenError()

        }
    }

}
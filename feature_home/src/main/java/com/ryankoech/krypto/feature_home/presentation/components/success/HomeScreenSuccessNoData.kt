package com.ryankoech.krypto.feature_home.presentation.components.success

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ryankoech.krypto.common.presentation.ErrorScreen
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme
import ke.co.sevenskies.feature_home.R

@Composable
fun HomeScreenSuccessNoData(
    modifier: Modifier = Modifier
){
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
fun HomeScreenSuccessNoDataPreview() {

    KryptoTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {

            HomeScreenSuccessNoData()

        }
    }

}
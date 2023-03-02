package com.ryankoech.krypto.feature_coin_details.presentation

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ryankoech.krypto.common.R
import com.ryankoech.krypto.common.presentation.ErrorScreen
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme

@Composable
fun CoinDetailsScreenError(
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
){

    ErrorScreen(modifier = modifier, onButtonClick = onButtonClick, messageText = "Jeez, something's amiss", buttonText = "Retry", res = R.drawable.icon_gif_search)

}

@Preview
@Composable
fun CoinDetailsScreenErrorPreview (){
    KryptoTheme {
        Surface {
            CoinDetailsScreenError(
                onButtonClick = { /*TODO*/ }
            )
        }
    }
}
package com.ryankoech.krypto.feature_coin_details.presentation

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ryankoech.krypto.common.R
import com.ryankoech.krypto.common.presentation.ErrorScreen
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme

@Composable
fun CoinDetailsScreenError(
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
){

    ErrorScreen(modifier = modifier, onButtonClick = onButtonClick, messageText = stringResource(com.ryankoech.krypto.feature_coin_details.R.string.message_error_screen), buttonText = stringResource(com.ryankoech.krypto.feature_coin_details.R.string.button_retry), res = R.drawable.icon_gif_search)

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
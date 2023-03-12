package com.ryankoech.krypto.feature_coin_list.presentation

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ryankoech.krypto.common.presentation.ErrorScreen
import com.ryankoech.krypto.common.R
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme

const val TEST_TAG_COIN_LIST_SCREEN_ERROR = "test tag coin list screen error"

@Composable
fun CoinListScreenError(
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
){

    ErrorScreen(modifier = modifier, onButtonClick = onButtonClick, messageText = "Hmm, can't seem to find any coins", buttonText = "Try Again", res = R.drawable.icon_gif_caution)

}

@Preview
@Composable
fun CoinListScreenErrorPreview(){

    KryptoTheme {
        Surface {
            CoinListScreenError(onButtonClick = {})
        }
    }

}
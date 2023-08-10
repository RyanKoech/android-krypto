package com.ryankoech.krypto.feature_transaction.presentation.choose_asset

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ryankoech.krypto.common.presentation.ErrorScreen
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme
import com.ryankoech.krypto.common.R
import com.ryankoech.krypto.common.presentation.util.KryptoPreview

@Composable
fun ChooseAssetScreenError(
    onRetryButtonClick : () -> Unit,
    modifier: Modifier = Modifier
) {
    ErrorScreen(
        modifier = modifier,
        messageText = "Hmm, it's kinda empty here.",
        res = R.drawable.icon_gif_caution,
        onButtonClick = onRetryButtonClick,
    )
}

@KryptoPreview
@Composable
fun ChooseAssetScreenErrorPreview() {

    KryptoTheme {
        Surface {
            ChooseAssetScreenError({})
        }
    }

}
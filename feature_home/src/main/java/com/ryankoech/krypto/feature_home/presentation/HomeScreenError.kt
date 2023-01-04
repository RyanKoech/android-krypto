package com.ryankoech.krypto.feature_home.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ryankoech.krypto.common.presentation.ErrorScreen
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme
import com.ryankoech.krypto.common.R as commonR
import com.ryankoech.krypto.feature_home.R

const val TEST_TAG_HOME_SCREEN_ERROR = "test_tag_home_screen_error"

@Composable
fun HomeScreenError(
    modifier : Modifier = Modifier
) {
    ErrorScreen(
        modifier = modifier,
        onButtonClick = {},
        messageText = stringResource(R.string.home_screen_error_message),
        buttonText = stringResource(R.string.home_screen_error_action),
        res = commonR.drawable.error,
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
package com.ryankoech.krypto.feature_home.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme
import com.ryankoech.krypto.common.presentation.util.ScreenState
import com.ryankoech.krypto.common.presentation.util.collectAsEffect
import com.ryankoech.krypto.feature_home.presentation.viewmodel.HomeScreenViewModel

@Composable
fun HomeScreen(
    onTransferInClick : () -> Unit,
    onTransferOutClick : () -> Unit,
    navigateToCoinDetails : (String) -> Unit,
    viewModel: HomeScreenViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current
    val viewState = viewModel.viewState.value

    viewModel.message.collectAsEffect { toastMessage ->
        if(toastMessage.isNotEmpty()){
            Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
        }
    }

    Surface(
        modifier = modifier
            .fillMaxSize(),
    ) {
        when (viewState.screenState) {
            ScreenState.LOADING -> {
                HomeScreenLoading(
                    modifier = Modifier.testTag(TEST_TAG_HOME_SCREEN_LOADING)
                )
            }
            ScreenState.ERROR -> {
                HomeScreenError(
                    modifier = Modifier.testTag(TEST_TAG_HOME_SCREEN_ERROR)
                )
            }
            ScreenState.SUCCESS -> {
                HomeScreenSuccess(
                    modifier = Modifier.testTag(TEST_TAG_HOME_SCREEN_SUCCESS ),
                    ownedCoins = viewState.ownedCoins,
                    displayCurrencies = viewState.displayCurrencies,
                    onTransferInClick = onTransferInClick,
                    onTransferOutClick = onTransferOutClick,
                    navigateToCoinDetails = navigateToCoinDetails,
                    onWipeWalletClick = { viewModel.wipeOwnedCoinsDatabase() }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    KryptoTheme {
        HomeScreen({}, {}, {})
    }
}
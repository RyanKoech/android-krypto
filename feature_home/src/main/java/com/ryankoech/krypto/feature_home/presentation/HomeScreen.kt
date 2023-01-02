package com.ryankoech.krypto.feature_home.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
    viewModel: HomeScreenViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current
    val viewState = viewModel.viewState.value

    viewModel._message.collectAsEffect { toastMessage ->
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
                HomeScreenLoading()
            }
            ScreenState.ERROR -> {
                HomeScreenError()
            }
            ScreenState.SUCCESS -> {
                HomeScreenSuccess(
                    ownedCoins = viewState.ownedCoins,
                    displayCurrencies = viewState.displayCurrencies,
                    onTransferInClick = onTransferInClick,
                    onTransferOutClick = onTransferOutClick,
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
        HomeScreen({}, {})
    }
}
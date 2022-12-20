package com.ryankoech.krypto.feature_home.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme
import com.ryankoech.krypto.common.presentation.util.ScreenState
import com.ryankoech.krypto.feature_home.presentation.viewmodel.HomeScreenViewModel

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {

    val viewState = viewModel.viewState.value

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
                HomeScreenSuccess(viewState.ownedCoins, viewState.displayCurrencies)

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    KryptoTheme {
        HomeScreen()
    }
}
package com.ryankoech.krypto.feature_coin_list.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme
import com.ryankoech.krypto.common.presentation.util.ScreenState
import com.ryankoech.krypto.feature_coin_list.presentation.viewmodel.CoinListScreenViewModel

@Composable
fun CoinListScreen(
    viewModel: CoinListScreenViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {

    val viewState = viewModel.viewState.value

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        when(viewState.screenState){
            ScreenState.LOADING -> {
                //TODO : Show Loading Screen
            }
            ScreenState.ERROR -> {
                //TODO : Show Error Screen
            }
            ScreenState.SUCCESS -> {
                CoinListScreenSuccess()
            }
        }
    }
}

@Preview
@Composable
fun CoinListScreenPreview() {

    KryptoTheme {
        CoinListScreen()
    }
}
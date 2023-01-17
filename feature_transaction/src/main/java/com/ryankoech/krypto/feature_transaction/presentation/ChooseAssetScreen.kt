package com.ryankoech.krypto.feature_transaction.presentation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme
import com.ryankoech.krypto.common.presentation.util.ScreenState
import com.ryankoech.krypto.feature_transaction.presentation.choose_asset.ChooseAssetScreenSuccess
import com.ryankoech.krypto.feature_transaction.presentation.choose_asset.viewmodel.ChooseAssetScreenViewModel

@Composable
fun ChooseAssetScreen(
    viewModel : ChooseAssetScreenViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {

    val viewState = viewModel.viewState.value
    var searchValue by remember {
        mutableStateOf("")
    }

    fun editSearchValueState(newValue : String ){
        viewModel.getCoinList(newValue)
        searchValue = newValue
    }

    when(viewState.screenState){
        ScreenState.LOADING -> {

        }
        ScreenState.ERROR -> {

        }
        ScreenState.SUCCESS -> {

            ChooseAssetScreenSuccess(
                onChooseAssetItemClick = {},
                coins = viewState.coins,
                searchValue = searchValue,
                onSearchValueChange = ::editSearchValueState
            )

        }
    }

}

@Preview
@Composable
fun PreviewChooseAssetScreen() {

    KryptoTheme {
        Surface {
            ChooseAssetScreen()
        }
    }

}
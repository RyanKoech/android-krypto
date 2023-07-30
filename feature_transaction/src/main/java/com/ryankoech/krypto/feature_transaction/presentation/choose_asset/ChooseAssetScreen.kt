package com.ryankoech.krypto.feature_transaction.presentation.choose_asset

import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme
import com.ryankoech.krypto.common.presentation.util.KryptoPreview
import com.ryankoech.krypto.common.presentation.util.ScreenState
import com.ryankoech.krypto.feature_transaction.data.dto.transaction_dto.TransactionType
import com.ryankoech.krypto.feature_transaction.domain.entity.Coin
import com.ryankoech.krypto.feature_transaction.presentation.choose_asset.viewmodel.ChooseAssetScreenViewModel

@Composable
fun ChooseAssetScreen(
    toTransactionScreen : (Coin, String) -> Unit,
    toTransactionType: String,
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
            ChooseAssetScreenLoading(modifier)
        }
        ScreenState.ERROR -> {
            ChooseAssetScreenError(
                modifier = modifier,
                onRetryButtonClick = {
                    viewModel.getCoinList(searchValue)
                }
            )
        }
        ScreenState.SUCCESS -> {

            ChooseAssetScreenSuccess(
                modifier = modifier,
                onChooseAssetItemClick = { coin ->
                    toTransactionScreen(coin, toTransactionType)
                },
                coins = viewState.coins,
                searchValue = searchValue,
                onSearchValueChange = ::editSearchValueState
            )

        }
    }

}

@KryptoPreview
@Composable
fun PreviewChooseAssetScreen() {

    KryptoTheme {
        Surface {
            ChooseAssetScreen(
                { _, _ -> },
                TransactionType.BUY.toString(),
            )
        }
    }

}
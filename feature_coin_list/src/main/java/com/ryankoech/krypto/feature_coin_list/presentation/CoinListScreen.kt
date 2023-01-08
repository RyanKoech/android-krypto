package com.ryankoech.krypto.feature_coin_list.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme
import com.ryankoech.krypto.common.presentation.util.ScreenState
import com.ryankoech.krypto.feature_coin_list.domain.entity.Order
import com.ryankoech.krypto.feature_coin_list.domain.entity.SortCoinBy
import com.ryankoech.krypto.feature_coin_list.domain.entity.SortInfo
import com.ryankoech.krypto.feature_coin_list.presentation.viewmodel.CoinListScreenViewModel

@Composable
fun CoinListScreen(
    viewModel: CoinListScreenViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {

    val viewState = viewModel.viewState.value
    var sortInfoState by remember {
        mutableStateOf(
            SortInfo(
                sortBy = SortCoinBy.MARKET_CAP,
                order = Order.DESC
            )
        )
    }

    fun editSortingInfo(sortCoinBy: SortCoinBy) {

        val newSortInfo = if(sortCoinBy == sortInfoState.sortBy){
            if(sortInfoState.order == Order.ASC) {
                sortInfoState.copy(
                    order = Order.DESC
                )
            }else {
                sortInfoState.copy(
                    order = Order.ASC
                )
            }
        }else {
            sortInfoState.copy(
                sortBy = sortCoinBy,
                order = Order.DESC
            )
        }

        viewModel.getCoins(newSortInfo)
        sortInfoState = newSortInfo
    }

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
                CoinListScreenSuccess(
                    editSortInfo = ::editSortingInfo,
                    sortInfoState = sortInfoState
                )
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
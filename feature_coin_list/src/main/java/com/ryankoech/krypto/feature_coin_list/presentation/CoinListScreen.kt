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
import timber.log.Timber

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

    var filterStringState by remember {
        mutableStateOf("")
    }

    fun editFilterStringState(subString :String) {
        viewModel.getCoins(sortInfoState, subString)
        filterStringState = subString
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

        viewModel.getCoins(newSortInfo, filterStringState)
        sortInfoState = newSortInfo
    }

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {

        if(viewState.screenState == ScreenState.ERROR) {
            //TODO : Show Error Screen
            Timber.d("Display Coin List Error Screen")
        }else {
            CoinListScreenSuccess(
                screenState = viewState.screenState,
                editSortInfo = ::editSortingInfo,
                sortInfoState = sortInfoState,
                coins = viewState.coins,
                filterStringState = filterStringState,
                editFilterStringState = ::editFilterStringState
            )
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
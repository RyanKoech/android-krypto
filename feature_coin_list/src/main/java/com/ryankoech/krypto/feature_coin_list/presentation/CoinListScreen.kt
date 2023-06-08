package com.ryankoech.krypto.feature_coin_list.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme
import com.ryankoech.krypto.common.presentation.util.ScreenState
import com.ryankoech.krypto.feature_coin_list.domain.entity.Coin
import com.ryankoech.krypto.feature_coin_list.domain.entity.Order
import com.ryankoech.krypto.feature_coin_list.domain.entity.SortCoinBy
import com.ryankoech.krypto.feature_coin_list.domain.entity.SortInfo
import com.ryankoech.krypto.feature_coin_list.presentation.viewmodel.CoinListScreenViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CoinListScreen(
    coinItemOnClick : (String) -> Unit,
    viewModel: CoinListScreenViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {

    val viewState = viewModel.viewState.value
    val refreshScope = rememberCoroutineScope()

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

    var isRefreshingState by remember {
        mutableStateOf(false)
    }

    fun refresh() = refreshScope.launch {
        isRefreshingState = true
        viewModel.getCoins(sortInfoState, filterStringState)
        delay(500)
        isRefreshingState = false
    }

    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshingState,
        onRefresh = ::refresh
    )

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
        modifier = modifier.fillMaxSize()
    ) {

        if(viewState.screenState == ScreenState.ERROR) {
            CoinListScreenError(
                modifier = Modifier
                    .testTag(TEST_TAG_COIN_LIST_SCREEN_ERROR),
                onButtonClick = { viewModel.getCoins(sortInfoState, filterStringState) }
            )
        }else {

            Box(
                modifier = Modifier
                    .pullRefresh(pullRefreshState)
            ){

                CoinListScreenSuccess(
                    modifier = Modifier
                        .testTag(TEST_TAG_COIN_LIST_SCREEN_SUCCESS),
                    coinItemOnClick = coinItemOnClick,
                    screenState = viewState.screenState,
                    editSortInfo = ::editSortingInfo,
                    sortInfoState = sortInfoState,
                    coins = viewState.coins,
                    filterStringState = filterStringState,
                    editFilterStringState = ::editFilterStringState
                )

                PullRefreshIndicator(
                    modifier = Modifier
                        .align(Alignment.TopCenter),
                    refreshing = isRefreshingState,
                    state = pullRefreshState,
                )

            }
        }
    }
}

@Preview
@Composable
fun CoinListScreenPreview() {

    KryptoTheme {
        CoinListScreen({})
    }
}
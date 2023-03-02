package com.ryankoech.krypto.feature_coin_details.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme
import com.ryankoech.krypto.common.presentation.util.*
import com.ryankoech.krypto.feature_coin_details.domain.entity.toPairList
import com.ryankoech.krypto.feature_coin_details.presentation.util.MarketChartRange
import com.ryankoech.krypto.feature_coin_details.presentation.viewmodel.CoinDetailsScreenViewModel
import com.ryankoech.krypto.feature_coin_list.data.dto.toCoinEntity
import com.ryankoech.krypto.feature_coin_list.data.repository.FAKE_COIN_LIST
import com.ryankoech.krypto.feature_coin_list.domain.entity.Coin
import timber.log.Timber

val MarketChartRangeText = listOf(
    Pair(MarketChartRange.ONE_HOUR, "1h"),
    Pair(MarketChartRange.ONE_DAY ,"1d"),
    Pair(MarketChartRange.ONE_WEEK ,"7d"),
    Pair(MarketChartRange.ONE_MONTH ,"30d"),
    Pair(MarketChartRange.THREE_MONTH ,"90d"),
    Pair(MarketChartRange.ONE_YEAR ,"1y"),
)

@Composable
fun CoinDetailsScreen(
    viewModel : CoinDetailsScreenViewModel = hiltViewModel(),
    coin : Coin,
    navigateToBuyTransactionScreen : (String) -> Unit,
    navigateToSellTransactionScreen : (String) -> Unit,
    modifier: Modifier = Modifier
) {

    LaunchedEffect(key1 = Unit){
        viewModel.getCoinDetails(coin.id)
        viewModel.getTransactions(coin.id)
    }

    val context = LocalContext.current
    val viewState = viewModel.viewState.value

    viewModel.message.collectAsEffect { toastMessage ->
        if(toastMessage.isNotEmpty()){
            Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
        }
    }

    var currentMarketChartRange by remember {
        mutableStateOf(MarketChartRange.ONE_DAY)
    }

    val marketChartButtonOnClick = { marketChartRange : MarketChartRange ->
        currentMarketChartRange = marketChartRange
    }

    var marketChartData by remember {
        mutableStateOf(listOf<Pair<Int, Double>>())
    }

    LaunchedEffect(key1 = currentMarketChartRange, key2 = viewState){
        marketChartData = when(currentMarketChartRange){
            MarketChartRange.ONE_HOUR -> {
                // Every 5 min hence first 12 = 1 hour
                try {
                    viewState.dayMarketChart.toPairList().subList(0, 11)
                } catch (e : Throwable){
                    Timber.d(e.localizedMessage)
                    viewState.dayMarketChart.toPairList()
                }
            }
            MarketChartRange.ONE_DAY -> {
                viewState.dayMarketChart.toPairList()
            }
            MarketChartRange.ONE_WEEK -> {
                // Every 1 hour hence first (24 hours per day * 7 days) hours
                try {
                    viewState.threeMonthMarketChart.toPairList().subList(0, 167)
                } catch (e : Throwable){
                    Timber.d(e.localizedMessage)
                    viewState.threeMonthMarketChart.toPairList()
                }
            }
            MarketChartRange.ONE_MONTH -> {
                // Every 1 hour hence first (24 hours per day * 30 days) hours
                try {
                    viewState.threeMonthMarketChart.toPairList().subList(0, 719)
                } catch (e : Throwable){
                    Timber.d(e.localizedMessage)
                    viewState.threeMonthMarketChart.toPairList()
                }
            }
            MarketChartRange.THREE_MONTH -> {
                viewState.threeMonthMarketChart.toPairList()
            }
            MarketChartRange.ONE_YEAR -> {
                viewState.yearMarketChart.toPairList()
            }
        }
    }

    when(viewState.screenState){
        ScreenState.LOADING -> {
            CoinDetailsScreenLoading(
                modifier = modifier,
            )
        }
        ScreenState.ERROR -> {

        }
        ScreenState.SUCCESS -> {

            CoinDetailsScreenSuccess(
                modifier = modifier,
                coin = coin,
                transactions = viewState.transactions,
                navigateToBuyTransactionScreen = navigateToBuyTransactionScreen,
                navigateToSellTransactionScreen = navigateToSellTransactionScreen,
                marketChartData = marketChartData,
                marketChartButtonOnClick = marketChartButtonOnClick,
                currentMarketChartRange = currentMarketChartRange
            )

        }
    }

}

@Preview
@Composable
fun CoinDetailsScreenPreview() {

    KryptoTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            CoinDetailsScreen(
                coin = FAKE_COIN_LIST.toCoinEntity().first(),
                navigateToBuyTransactionScreen = {},
                navigateToSellTransactionScreen = {},
            )
        }
    }

}
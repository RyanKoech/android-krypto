package com.ryankoech.krypto.feature_coin_details.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme
import com.ryankoech.krypto.common.presentation.util.*
import com.ryankoech.krypto.feature_coin_details.domain.entity.toPairList
import com.ryankoech.krypto.feature_coin_details.presentation.util.MarketChartRange
import com.ryankoech.krypto.feature_coin_details.presentation.viewmodel.CoinDetailsScreenViewModel
import com.ryankoech.krypto.feature_coin_list.data.dto.toCoinEntity
import com.ryankoech.krypto.feature_coin_list.data.repository.FAKE_COIN_LIST
import com.ryankoech.krypto.feature_transaction.domain.entity.Coin as TransactionCoin
import timber.log.Timber

@Composable
fun CoinDetailsScreen(
    viewModel : CoinDetailsScreenViewModel = hiltViewModel(),
    coinId : String,
    navigateToBuyTransactionScreen : (TransactionCoin) -> Unit,
    navigateToSellTransactionScreen : (TransactionCoin) -> Unit,
    modifier: Modifier = Modifier
) {

    LaunchedEffect(key1 = Unit){
        viewModel.getCoin(coinId)
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
                val list  = viewState.dayMarketChart.toPairList()
                if (list.size >= 12) {
                    list.subList(0, 12)
                } else {
                    viewState.dayMarketChart.toPairList()
                }
            }
            MarketChartRange.ONE_DAY -> {
                viewState.dayMarketChart.toPairList()
            }
            MarketChartRange.ONE_WEEK -> {
                // Every 1 hour hence first (24 hours per day * 7 days) hours
                val list  = viewState.threeMonthMarketChart.toPairList()
                if (list.size >= 168) {
                    list.subList(0, 168)
                } else {
                    viewState.threeMonthMarketChart.toPairList()
                }
            }
            MarketChartRange.ONE_MONTH -> {
                // Every 1 hour hence first (24 hours per day * 30 days) hours
                val list  = viewState.threeMonthMarketChart.toPairList()
                if (list.size >= 720) {
                    list.subList(0, 720)
                } else {
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
            CoinDetailsScreenError(
                onButtonClick = {
                    viewModel.getCoinDetails(coinId)
                }
            )
        }
        ScreenState.SUCCESS -> {

            CoinDetailsScreenSuccess(
                modifier = modifier,
                coin = viewState.coin,
                transactions = viewState.transactions,
                navigateToBuyTransactionScreen = navigateToBuyTransactionScreen,
                navigateToSellTransactionScreen = navigateToSellTransactionScreen,
                marketChartData = marketChartData,
                marketChartButtonOnClick = marketChartButtonOnClick,
                currentMarketChartRange = currentMarketChartRange,
                onDeleteTransactionClick = { date ->
                    viewModel.deleteCoinTransaction(date)
                }
            )

        }
    }

}

@KryptoPreview
@Composable
fun CoinDetailsScreenPreview() {

    KryptoTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            CoinDetailsScreen(
                coinId = "bitcoin",
                navigateToBuyTransactionScreen = {},
                navigateToSellTransactionScreen = {},
            )
        }
    }

}
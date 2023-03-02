package com.ryankoech.krypto.feature_coin_details.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ryankoech.krypto.common.presentation.ErrorScreen
import com.ryankoech.krypto.common.presentation.components.KryptoButton
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme
import com.ryankoech.krypto.common.presentation.theme.Red100
import com.ryankoech.krypto.common.presentation.util.collectAsEffect
import com.ryankoech.krypto.common.presentation.util.getFormattedBalance
import com.ryankoech.krypto.common.presentation.util.getFormattedMarketCap
import com.ryankoech.krypto.common.presentation.util.getFormattedTotalVolume
import com.ryankoech.krypto.feature_coin_details.R
import com.ryankoech.krypto.feature_coin_details.domain.entity.toPairList
import com.ryankoech.krypto.feature_coin_details.presentation.components.success.*
import com.ryankoech.krypto.feature_coin_details.presentation.util.CoinStatistic
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

    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){

        item{
            CoinDetailsHeader(
                coin = coin,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
            )
        }

        item {
            LineChart(
                data = marketChartData,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
                    .height(300.dp)
                    .background(color = Color.White)
            )
        }


        item{

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ){

                items(MarketChartRangeText.size){ counter ->
                    MarketRangeButton(
                        onClick = { marketChartButtonOnClick(MarketChartRangeText[counter].first) },
                        text = MarketChartRangeText[counter].second,
                        selected = MarketChartRangeText[counter].first == currentMarketChartRange
                    )

                }

            }

        }

        item{
            Divider(
                modifier = Modifier.fillMaxWidth(),
                color = Color(0xfff2f2f2)
            )
        }

        item{
            Text(
                modifier = Modifier
                    .padding(horizontal = 12.dp),
                text = "Coin Statistics",
                style = MaterialTheme.typography.h2,
            )
        }


        item{

            val coinStatistics = listOf(
                CoinStatistic("Market Cap", getFormattedMarketCap(context, coin.marketCap), R.drawable.icon_pie_chart),
                CoinStatistic("All Time High", getFormattedBalance(context, coin.allTimeHigh), R.drawable.icon_rocket),
                CoinStatistic("24hr High", getFormattedBalance(context, coin.high24Hr), R.drawable.iconi_trophie),
                CoinStatistic("Total Volume", getFormattedTotalVolume(context, coin.totalVolume.toLong()), R.drawable.icon_line_graph),
            )

            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(216.dp),
                contentPadding = PaddingValues(horizontal = 12.dp),
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalArrangement = Arrangement.SpaceAround,
                userScrollEnabled = false,
            ) {

                items(coinStatistics) { coinStatistic ->
                    CoinStatisticsCard(
                        resId = coinStatistic.icon,
                        title = coinStatistic.title ,
                        body = coinStatistic.message
                    )
                }

            }

        }

        item{
            Divider(
                modifier = Modifier.fillMaxWidth(),
                color = Color(0xfff2f2f2)
            )
        }


        item{
            Text(
                modifier = Modifier
                    .padding(horizontal = 12.dp),
                text = "Transaction History",
                style = MaterialTheme.typography.h2,
            )
        }
        
        if(viewState.transactions.isEmpty()) {

            item{
                ErrorScreen(
                    modifier = Modifier
                        .padding(
                            vertical = 40.dp,
                        ),
                    messageText = "Whoops, looks like a ghost town here.",
                    res = com.ryankoech.krypto.feature_transaction.R.drawable.no_transaction,
                    showButton = false,
                )
            }
            
        }else {

            items(viewState.transactions){ transaction ->

                TransactionItem(
                    modifier = Modifier
                        .padding(
                            start = 12.dp,
                            end = 12.dp,
                            bottom = 12.dp,

                        ),
                    transaction = transaction
                )

            }
        }

        item {

            Row(
                modifier = Modifier
                    .padding(
                        horizontal = 12.dp,
                    )
                    .fillMaxWidth(),
            ) {

                KryptoButton(
                    modifier = Modifier
                        .weight(1.0f),
                    text = "Transfer In",
                    onClick = {
                        navigateToBuyTransactionScreen(coin.id)
                    }
                )

                Spacer(modifier = Modifier.width(24.dp))

                KryptoButton(
                    modifier = Modifier
                        .weight(1.0f),
                    text = "Transfer Out",
                    onClick = {
                        navigateToSellTransactionScreen(coin.id)
                    },
                    color = Red100
                )

            }

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
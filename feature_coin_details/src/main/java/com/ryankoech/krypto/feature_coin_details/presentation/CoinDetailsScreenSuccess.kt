package com.ryankoech.krypto.feature_coin_details.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ryankoech.krypto.common.presentation.ErrorScreen
import com.ryankoech.krypto.common.presentation.components.KryptoButton
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme
import com.ryankoech.krypto.common.presentation.theme.Red100
import com.ryankoech.krypto.common.presentation.util.getFormattedBalance
import com.ryankoech.krypto.common.presentation.util.getFormattedMarketCap
import com.ryankoech.krypto.common.presentation.util.getFormattedTotalVolume
import com.ryankoech.krypto.feature_coin_details.R
import com.ryankoech.krypto.feature_coin_details.presentation.components.success.*
import com.ryankoech.krypto.feature_coin_details.presentation.util.CoinStatistic
import com.ryankoech.krypto.feature_coin_details.presentation.util.MarketChartRange
import com.ryankoech.krypto.feature_coin_list.data.dto.toCoinEntity
import com.ryankoech.krypto.feature_coin_list.data.repository.FAKE_COIN_LIST
import com.ryankoech.krypto.feature_coin_list.domain.entity.Coin
import com.ryankoech.krypto.feature_transaction.data.dto.transaction_dto.TransactionDto
import com.ryankoech.krypto.feature_transaction.data.repository.bitCoinTransaction

@Composable
fun CoinDetailsScreenSuccess(
    modifier : Modifier = Modifier,
    coin : Coin,
    transactions : List<TransactionDto>,
    navigateToBuyTransactionScreen : (String) -> Unit,
    navigateToSellTransactionScreen : (String) -> Unit,
    marketChartData : List<Pair<Int, Double>>,
    marketChartButtonOnClick : (MarketChartRange) -> Unit,
    currentMarketChartRange : MarketChartRange,
) {

    val context = LocalContext.current

    val marketChartRangeText = listOf(
        Pair(MarketChartRange.ONE_HOUR, context.getString(R.string.button_market_chart_hour)),
        Pair(MarketChartRange.ONE_DAY ,context.getString(R.string.button_market_chart_day)),
        Pair(MarketChartRange.ONE_WEEK ,context.getString(R.string.button_market_chart_week)),
        Pair(MarketChartRange.ONE_MONTH ,context.getString(R.string.button_market_chart_month)),
        Pair(MarketChartRange.THREE_MONTH ,context.getString(R.string.button_market_chart_three_months)),
        Pair(MarketChartRange.ONE_YEAR ,context.getString(R.string.button_market_chart_year)),
    )

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

                items(marketChartRangeText.size){ counter ->
                    MarketRangeButton(
                        onClick = { marketChartButtonOnClick(marketChartRangeText[counter].first) },
                        text = marketChartRangeText[counter].second,
                        selected = marketChartRangeText[counter].first == currentMarketChartRange
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
                text = stringResource(R.string.heading_coin_statistics),
                style = MaterialTheme.typography.h2,
            )
        }


        item{

            val coinStatistics = listOf(
                CoinStatistic(stringResource(R.string.card_heading_market_cap), getFormattedMarketCap(context, coin.marketCap), R.drawable.icon_pie_chart),
                CoinStatistic(stringResource(R.string.card_heading_all_time_high), getFormattedBalance(context, coin.allTimeHigh), R.drawable.icon_rocket),
                CoinStatistic(stringResource(R.string.card_heading_24hr_high), getFormattedBalance(context, coin.high24Hr), R.drawable.iconi_trophie),
                CoinStatistic(stringResource(R.string.card_heading_total_volume), getFormattedTotalVolume(context, coin.totalVolume.toLong()), R.drawable.icon_line_graph),
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
                text = stringResource(R.string.heading_transaction_history),
                style = MaterialTheme.typography.h2,
            )
        }

        if(transactions.isEmpty()) {

            item{
                ErrorScreen(
                    modifier = Modifier
                        .padding(
                            vertical = 40.dp,
                        ),
                    messageText = stringResource(R.string.message_no_transactions),
                    res = com.ryankoech.krypto.feature_transaction.R.drawable.no_transaction,
                    showButton = false,
                )
            }

        }else {

            items(transactions){ transaction ->

                TransactionItem(
                    modifier = Modifier
                        .padding(
                            start = 12.dp,
                            end = 12.dp,
                            bottom = 12.dp,

                            ),
                    transaction = transaction,
                    symbol = coin.symbol
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
                    text = stringResource(R.string.button_transfer_in),
                    onClick = {
                        navigateToBuyTransactionScreen(coin.id)
                    }
                )

                Spacer(modifier = Modifier.width(24.dp))

                KryptoButton(
                    modifier = Modifier
                        .weight(1.0f),
                    text = stringResource(R.string.button_transfer_out),
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
fun CoinDetailsScreenSuccessPreview() {

    KryptoTheme {
        Surface {
            CoinDetailsScreenSuccess(
                coin = FAKE_COIN_LIST.toCoinEntity().first(),
                navigateToBuyTransactionScreen = {},
                navigateToSellTransactionScreen = {},
                currentMarketChartRange = MarketChartRange.ONE_DAY,
                marketChartButtonOnClick = {},
                marketChartData = FAKE_LINECHART_DATA,
                transactions = bitCoinTransaction,
            )
        }
    }

}
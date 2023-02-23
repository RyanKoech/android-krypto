package com.ryankoech.krypto.feature_coin_details.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme
import com.ryankoech.krypto.common.presentation.util.getFormattedBalance
import com.ryankoech.krypto.common.presentation.util.getFormattedMarketCap
import com.ryankoech.krypto.common.presentation.util.getFormattedTotalVolume
import com.ryankoech.krypto.feature_coin_details.R
import com.ryankoech.krypto.feature_coin_details.presentation.components.success.CoinDetailsHeader
import com.ryankoech.krypto.feature_coin_details.presentation.components.success.CoinStatisticsCard
import com.ryankoech.krypto.feature_coin_details.presentation.components.success.LineChart
import com.ryankoech.krypto.feature_coin_details.presentation.components.success.MarketRangeButton
import com.ryankoech.krypto.feature_coin_details.presentation.util.CoinStatistic
import com.ryankoech.krypto.feature_coin_details.presentation.util.MarketChartRange
import com.ryankoech.krypto.feature_coin_list.data.dto.toCoinEntity
import com.ryankoech.krypto.feature_coin_list.data.repository.FAKE_COIN_LIST
import com.ryankoech.krypto.feature_coin_list.domain.entity.Coin

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
    // viewModel : CoinDetailsScreenViewModel = hiltViewModel(),
    coin : Coin,
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current

    var currentMarketChartRange by remember {
        mutableStateOf(MarketChartRange.ONE_DAY)
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
            val data = listOf(
                Pair(6, 111.45),
                Pair(7, 111.0),
                Pair(8, 113.45),
                Pair(9, 112.25),
                Pair(10, 116.45),
                Pair(11, 113.35),
                Pair(12, 118.65),
                Pair(13, 110.15),
                Pair(14, 113.05),
                Pair(15, 114.25),
                Pair(16, 116.35),
                Pair(17, 117.45),
                Pair(18, 112.65),
                Pair(19, 115.45),
                Pair(20, 111.85)
            )
            LineChart(
                data = data,
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
                        onClick = { /*TODO*/ },
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
                CoinStatistic("All Time High", getFormattedBalance(context, coin.allTimeHigh), R.drawable.icon_pie_chart),
                CoinStatistic("24hr High", getFormattedBalance(context, coin.high24Hr), R.drawable.icon_pie_chart),
                CoinStatistic("Total Volume", getFormattedTotalVolume(context, coin.totalVolume.toLong()), R.drawable.icon_pie_chart),
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
            )
        }
    }

}
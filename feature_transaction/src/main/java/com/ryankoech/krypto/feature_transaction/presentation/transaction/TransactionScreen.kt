package com.ryankoech.krypto.feature_transaction.presentation.transaction

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme
import com.ryankoech.krypto.feature_coin_list.data.repository.FAKE_COIN_LIST
import com.ryankoech.krypto.feature_transaction.data.dto.transaction_dto.TransactionDto
import com.ryankoech.krypto.feature_transaction.domain.entity.Coin
import com.ryankoech.krypto.feature_transaction.domain.entity.toCoinEntity
import com.ryankoech.krypto.feature_transaction.presentation.transaction.components.BuyTransactionScreen
import com.ryankoech.krypto.feature_transaction.presentation.transaction.components.SellTransactionScreen
import com.ryankoech.krypto.feature_transaction.presentation.transaction.util.TabRowItem
import com.ryankoech.krypto.feature_transaction.presentation.transaction.viewmodel.TransactionScreenViewModel
import kotlinx.coroutines.launch
import java.util.*

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TransactionScreen(
    coin : Coin,
    viewModel : TransactionScreenViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {

    val viewState = viewModel.viewState.value

    LaunchedEffect(key1 = Unit){
        viewModel.getCoinAmount(coin.id)
    }

    val onTransactionButtonClick = { transaction : TransactionDto ->
        viewModel.saveCoinTransaction(transaction)
    }

    val tabRowItems = listOf(
        TabRowItem(
            title = "Buy",
            screen = {
                BuyTransactionScreen(
                    coin = coin,
                    onTransactionButtonClick = onTransactionButtonClick,
                    screenState = viewState.screenState
                )
            },
        ),
        TabRowItem(
            title = "SELL",
            screen = {
                SellTransactionScreen(
                    coin = coin,
                    onTransactionButtonClick = onTransactionButtonClick,
                    ownedCoinDto = viewState.ownedCoin,
                    screenState = viewState.screenState
                )
            },
        ),
    )

    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = modifier
    ) {
        TabRow(
            modifier = Modifier
                .shadow(
                    elevation = 4.dp
                ),
            selectedTabIndex = pagerState.currentPage,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                    color = Color(0xff1975d8)
                )
            },
        ) {
            tabRowItems.forEachIndexed { index, item ->
                Tab(
                    modifier = Modifier
                        .background(
                            color = Color.White
                        ),
                    selected = pagerState.currentPage == index,
                    onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
                    text = {
                        Text(
                            text = item.title.uppercase(Locale.ROOT),
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.body1,
                            color = if (pagerState.currentPage == index) Color.Black else Color.LightGray
                        )
                    }
                )
            }
        }
        HorizontalPager(
            count = tabRowItems.size,
            state = pagerState,
        ) {
            tabRowItems[pagerState.currentPage].screen()
        }
    }

}

@Preview
@Composable
fun TransactionScreenPreview() {

    KryptoTheme {
        Surface {
            TransactionScreen(
                FAKE_COIN_LIST.toCoinEntity().first(),
            )
        }
    }

}
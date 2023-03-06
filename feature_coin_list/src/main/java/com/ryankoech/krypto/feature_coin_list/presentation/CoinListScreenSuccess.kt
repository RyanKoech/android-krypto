package com.ryankoech.krypto.feature_coin_list.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ryankoech.krypto.common.presentation.components.SearchBar
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme
import com.ryankoech.krypto.common.presentation.util.ScreenState
import com.ryankoech.krypto.feature_coin_list.data.dto.toCoinEntity
import com.ryankoech.krypto.feature_coin_list.data.repository.FAKE_COIN_LIST
import com.ryankoech.krypto.feature_coin_list.domain.entity.Coin
import com.ryankoech.krypto.feature_coin_list.domain.entity.SortCoinBy
import com.ryankoech.krypto.feature_coin_list.domain.entity.SortInfo
import com.ryankoech.krypto.feature_coin_list.presentation.components.success.*
import com.ryankoech.krypto.feature_coin_list.presentation.viewmodel.CoinListScreenViewModel.Companion.DEFAULT_SORT_INFO
import timber.log.Timber

const val TEST_TAG_COIN_LIST_SCREEN_SUCCESS = "test tag coin list screen success"
const val TEST_TAG_COIN_LIST_SCREEN_SUCCESS_COINS_LAZY_COLUMN = "test tag coin list screen success coins lazy column"

@Composable
fun CoinListScreenSuccess(
    coinItemOnClick : (Coin) -> Unit,
    screenState : ScreenState,
    editSortInfo : (SortCoinBy) -> Unit,
    sortInfoState : SortInfo,
    coins : List<Coin>,
    filterStringState : String,
    editFilterStringState : (String) -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                top = 12.dp,
            )
    ) {

        SearchBar(
            modifier = Modifier
                .padding(horizontal = 12.dp),
            value = filterStringState,
            onValueChange = editFilterStringState,
            placeholder = "Discover asset, coin or token"
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp, start = 12.dp, end = 12.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ){

            SearchTag(
                editSortingInfo = editSortInfo,
                sortInfoState = sortInfoState,
                sortCoinBy = SortCoinBy.MARKET_CAP,
                text = "Market Cap"
            )

            SearchTag(
                editSortingInfo = editSortInfo,
                sortInfoState = sortInfoState,
                sortCoinBy = SortCoinBy.TOTAL_VOLUME,
                text = "Volume"
            )

            SearchTag(
                editSortingInfo = editSortInfo,
                sortInfoState = sortInfoState,
                sortCoinBy = SortCoinBy.PRICE,
                text = "Price"
            )
        }

        if(screenState == ScreenState.LOADING) {
            CoinListLoading(
                modifier = Modifier
                    .testTag(TEST_TAG_COIN_LIST_LOADING)
            )
        } else {

            if( coins.isEmpty() ) {
                NoCoinsFound(
                    modifier = Modifier
                        .testTag(TEST_TAG_NO_COINS_FOUND)
                )
            }else {

                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .testTag(TEST_TAG_COIN_LIST_SCREEN_SUCCESS_COINS_LAZY_COLUMN),
                    contentPadding = PaddingValues(
                        top =  12.dp, start = 12.dp, end = 12.dp
                    )
                ){
                    items(
                        items = coins,
                        key = { it.id }
                    ){ coin ->
                        CoinItem(onClick = coinItemOnClick, coin = coin)
                        Spacer(modifier = Modifier.height(24.dp))
                    }
                }
            }
        }
    }

}

@Preview
@Composable
fun CoinListSuccessScreenPreview(){
    KryptoTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {

            CoinListScreenSuccess(
                coinItemOnClick = {},
                screenState = ScreenState.SUCCESS,
                editSortInfo = {},
                sortInfoState = DEFAULT_SORT_INFO,
                coins = FAKE_COIN_LIST.toCoinEntity(),
                filterStringState = "",
                editFilterStringState = {}
            )

        }
    }
}
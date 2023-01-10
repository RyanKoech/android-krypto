package com.ryankoech.krypto.feature_coin_list.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
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
import com.ryankoech.krypto.feature_coin_list.presentation.components.success.CoinItem
import com.ryankoech.krypto.feature_coin_list.presentation.components.success.CoinListLoading
import com.ryankoech.krypto.feature_coin_list.presentation.components.success.NoCoinsFound
import com.ryankoech.krypto.feature_coin_list.presentation.components.success.SearchTag
import com.ryankoech.krypto.feature_coin_list.presentation.viewmodel.DEFAULT_SORT_INFO
import timber.log.Timber

@Composable
fun CoinListScreenSuccess(
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
            CoinListLoading()
        } else {

            if( coins.isEmpty() ) {
                NoCoinsFound()
            }else {

                LazyColumn(
                    modifier = Modifier
                        .weight(1f),
                    contentPadding = PaddingValues(
                        top =  12.dp, start = 12.dp, end = 12.dp
                    )
                ){
                    items(
                        items = coins,
                        key = { it.id }
                    ){ coin ->
                        CoinItem(onClick = {}, coin = coin)
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
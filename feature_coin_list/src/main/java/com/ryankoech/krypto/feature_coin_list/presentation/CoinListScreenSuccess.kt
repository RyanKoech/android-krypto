package com.ryankoech.krypto.feature_coin_list.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ryankoech.krypto.common.presentation.components.SearchBar
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme
import com.ryankoech.krypto.feature_coin_list.domain.entity.SortCoinBy
import com.ryankoech.krypto.feature_coin_list.domain.entity.SortInfo
import com.ryankoech.krypto.feature_coin_list.presentation.components.success.SearchTag
import com.ryankoech.krypto.feature_coin_list.presentation.viewmodel.DEFAULT_SORT_INFO

@Composable
fun CoinListScreenSuccess(
    editSortInfo : (sortCoinBy : SortCoinBy) -> Unit,
    sortInfoState : SortInfo,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                top = 12.dp,
            )
    ) {
        var value by remember {
            mutableStateOf("")
        }

        SearchBar(
            modifier = Modifier
                .padding(horizontal = 12.dp),
            value = value,
            onValueChange = { value = it },
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
                editSortInfo = {},
                sortInfoState = DEFAULT_SORT_INFO,
            )

        }
    }
}
package com.ryankoech.krypto.feature_transaction.presentation.choose_asset

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ryankoech.krypto.common.presentation.ErrorScreen
import com.ryankoech.krypto.common.presentation.components.SearchBar
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme
import com.ryankoech.krypto.common.R as commonR
import com.ryankoech.krypto.feature_coin_list.data.repository.FAKE_COIN_LIST
import com.ryankoech.krypto.feature_transaction.domain.entity.Coin
import com.ryankoech.krypto.feature_transaction.domain.entity.toCoinEntity
import com.ryankoech.krypto.feature_transaction.presentation.choose_asset.components.success.ChooseAssetItem

@Composable
fun ChooseAssetScreenSuccess(
    onChooseAssetItemClick : (String) -> Unit,
    coins : List<Coin>,
    searchValue : String,
    onSearchValueChange : (String) -> Unit,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier
            .padding( 12.dp )
    ) {

        SearchBar(
            modifier = Modifier
                .padding(
                    bottom = 16.dp
                ),
            value = searchValue,
            onValueChange = onSearchValueChange,
            placeholder = "Look up asset, coin or token"
        )

        LazyColumn(
            modifier = Modifier
                .weight(1f),
        ) {

            if(coins.isEmpty()) {
                item {
                    ErrorScreen(
                        modifier = Modifier
                            .fillParentMaxHeight(),
                        messageText = "No assets to show.",
                        res = commonR.drawable.icon_gif_search
                    )
                }

            }else {
                items(items = coins){ coin ->
                    ChooseAssetItem(onChooseAssetItemClick, coin)
                }
            }
       }

    }

}

@Preview
@Composable
fun ChooseAssetScreenSuccessPreview () {

    KryptoTheme {
        Surface {
            ChooseAssetScreenSuccess(
                onChooseAssetItemClick = {},
                coins = FAKE_COIN_LIST.toCoinEntity(),
                searchValue = "",
                onSearchValueChange = {}
            )
        }
    }

}
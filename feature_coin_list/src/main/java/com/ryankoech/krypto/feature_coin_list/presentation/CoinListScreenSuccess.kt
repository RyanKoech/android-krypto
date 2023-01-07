package com.ryankoech.krypto.feature_coin_list.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ryankoech.krypto.common.presentation.components.SearchBar

@Composable
fun CoinListScreenSuccess(
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                top = 12.dp, start = 12.dp, end = 12.dp
            )
    ) {
        var value by remember {
            mutableStateOf("")
        }

        SearchBar(
            value = value,
            onValueChange = { value = it },
            placeholder = "Discover asset, coin or token"
        )
    }

}
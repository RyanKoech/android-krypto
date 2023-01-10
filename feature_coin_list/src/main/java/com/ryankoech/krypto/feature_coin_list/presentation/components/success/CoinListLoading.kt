package com.ryankoech.krypto.feature_coin_list.presentation.components.success

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ryankoech.krypto.common.presentation.components.loading.CoinCardLoading
import com.ryankoech.krypto.common.presentation.components.loading.loadingEffect
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme

@Composable
fun CoinListLoading(
    modifier: Modifier = Modifier
) {

    val brush = loadingEffect()

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            12.dp
        )
    ){
        items(
            count = 15
        ){
            CoinCardLoading(brush)
            Spacer(modifier = Modifier.height(24.dp))
        }
    }

}

@Preview
@Composable
fun CoinListScreenLoadingPreview() {
    KryptoTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            CoinListLoading()
        }
    }
}
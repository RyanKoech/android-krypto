package com.ryankoech.krypto.feature_home.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ryankoech.krypto.common.presentation.components.loading.CoinCardLoading
import com.ryankoech.krypto.common.presentation.components.loading.loadingEffect
import com.ryankoech.krypto.common.presentation.components.loading.LoadingText
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme
import com.ryankoech.krypto.common.presentation.components.loading.BigCardLoading
import com.ryankoech.krypto.feature_home.presentation.components.loading.HomeScreenActionsLoading

const val TEST_TAG_HOME_SCREEN_LOADING = "test_tag_home_screen_loading"

@Composable
fun HomeScreenLoading(
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .padding(top = 12.dp, start = 12.dp, end = 12.dp)
            .fillMaxSize()
    ){

        val brush = loadingEffect()

        BigCardLoading(brush)

        Spacer(modifier = Modifier.height(32.dp))

        HomeScreenActionsLoading(brush)

        Spacer(modifier = Modifier.height(32.dp))

        LoadingText(100.dp, brush)

        Spacer(modifier = Modifier.height(24.dp))

        CoinCardLoading(brush)

        Spacer(modifier = Modifier.height(12.dp))

        CoinCardLoading(brush)
    }

}

@Preview
@Composable
fun HomeScreenLoadingPreview() {

    KryptoTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            HomeScreenLoading()
        }
    }

}
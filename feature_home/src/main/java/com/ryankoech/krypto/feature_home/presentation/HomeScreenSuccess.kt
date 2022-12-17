package com.ryankoech.krypto.feature_home.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme
import com.ryankoech.krypto.feature_home.data.dto.owned_coin.OwnedCoinDto
import com.ryankoech.krypto.feature_home.data.repository.FakeOwnedCoins
import com.ryankoech.krypto.feature_home.presentation.components.success.*

@Composable
fun HomeScreenSuccess(
    ownedCoins : List<OwnedCoinDto>,
    modifier: Modifier = Modifier
) {

    if(ownedCoins.isEmpty()){
        HomeScreenSuccessNoData(modifier)
    }else{
        HomeScreenSuccessWithData(ownedCoins, modifier)
    }

}

@Preview
@Composable
fun HomeScreenSuccessPreview() {

    KryptoTheme {
        Surface(modifier = Modifier.fillMaxSize()) {

            HomeScreenSuccess(FakeOwnedCoins)

        }
    }

}
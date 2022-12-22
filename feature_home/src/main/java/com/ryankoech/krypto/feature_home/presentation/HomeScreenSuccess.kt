package com.ryankoech.krypto.feature_home.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme
import com.ryankoech.krypto.feature_home.data.dto.display_currency.DisplayCurrencyDto
import com.ryankoech.krypto.feature_home.data.dto.owned_coin.OwnedCoinDto
import com.ryankoech.krypto.feature_home.data.repository.FakeDisplayCurrencies
import com.ryankoech.krypto.feature_home.data.repository.FakeOwnedCoins
import com.ryankoech.krypto.feature_home.presentation.components.success.*

@Composable
fun HomeScreenSuccess(
    ownedCoins : List<OwnedCoinDto>,
    displayCurrencies : List<DisplayCurrencyDto>,
    onTransferInClick : () -> Unit,
    onTransferOutClick : () -> Unit,
    onWipeWalletClick : () -> Unit,
    modifier: Modifier = Modifier
) {

    if(ownedCoins.isEmpty()){
        HomeScreenSuccessNoData(modifier)
    }else{
        HomeScreenSuccessWithData(
            ownedCoins = ownedCoins,
            displayCurrencies = displayCurrencies,
            onTransferInClick = onTransferInClick,
            onTransferOutClick = onTransferOutClick,
            onWipeWalletClick = onWipeWalletClick,
            modifier = modifier
        )
    }

}

@Preview
@Composable
fun HomeScreenSuccessPreview() {

    KryptoTheme {
        Surface(modifier = Modifier.fillMaxSize()) {

            HomeScreenSuccess(FakeOwnedCoins, FakeDisplayCurrencies, {}, {}, {})

        }
    }

}
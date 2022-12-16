package com.ryankoech.krypto.feature_home.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme
import com.ryankoech.krypto.common.presentation.util.DisplayCurrency
import com.ryankoech.krypto.feature_home.data.dto.owned_coin.OwnedCoinDto
import com.ryankoech.krypto.feature_home.data.dto.owned_coin.getBalance
import com.ryankoech.krypto.feature_home.data.dto.owned_coin.getChange
import com.ryankoech.krypto.feature_home.data.repository.FakeOwnedCoins
import com.ryankoech.krypto.feature_home.presentation.components.success.CreditCard
import com.ryankoech.krypto.feature_home.presentation.components.success.HomeScreenActions
import com.ryankoech.krypto.feature_home.presentation.components.success.OwnedCoinItem
import com.ryankoech.krypto.feature_home.presentation.util.CreditCardDetails

@Composable
fun HomeScreenSuccess(
    ownedCoins : List<OwnedCoinDto>,
    modifier: Modifier = Modifier
) {

    val creditCardDetails = CreditCardDetails(
        balance = ownedCoins.getBalance(),
        change = ownedCoins.getChange(),
        count = ownedCoins.size,
        displayCurrency = DisplayCurrency.USD,
    )

    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(
            top = 12.dp, start = 12.dp, end = 12.dp
        )
    ) {

        item{
            CreditCard(
                onChangeDisplayCurrency = { println("Change  Currency") },
                creditCardDetails = creditCardDetails
            )
        }

        item{ Spacer(modifier = Modifier.height(32.dp)) }

        item{ HomeScreenActions() }

        item{ Spacer(modifier = Modifier.height(32.dp)) }

        item{
            Text(
                text = "My Portfolio",
                style = MaterialTheme.typography.h2,
            )
        }

        item{ Spacer(modifier = Modifier.height(24.dp)) }

        items(items = ownedCoins){ ownedCoin ->
            OwnedCoinItem(ownedCoin)
            Spacer(modifier = modifier.height(16.dp))
        }


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
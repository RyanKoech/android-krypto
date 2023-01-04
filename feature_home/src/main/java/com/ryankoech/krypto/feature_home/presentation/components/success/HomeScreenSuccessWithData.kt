package com.ryankoech.krypto.feature_home.presentation.components.success

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme
import com.ryankoech.krypto.feature_home.core.ktx.getNextIndex
import com.ryankoech.krypto.feature_home.data.dto.display_currency.DisplayCurrencyDto
import com.ryankoech.krypto.feature_home.data.dto.owned_coin.OwnedCoinDto
import com.ryankoech.krypto.feature_home.data.dto.owned_coin.getBalance
import com.ryankoech.krypto.feature_home.data.dto.owned_coin.getChange
import com.ryankoech.krypto.feature_home.data.repository.FakeDisplayCurrencies
import com.ryankoech.krypto.feature_home.data.repository.FakeOwnedCoins
import com.ryankoech.krypto.feature_home.presentation.util.CreditCardDetails

@Composable
fun HomeScreenSuccessWithData(
    ownedCoins : List<OwnedCoinDto>,
    displayCurrencies : List<DisplayCurrencyDto>,
    onTransferInClick : () -> Unit,
    onTransferOutClick : () -> Unit,
    onWipeWalletClick : () -> Unit,
    modifier: Modifier = Modifier,
) {

    var currentDisplayCurrencyIndex by remember {
        mutableStateOf(0)
    }

    var isDialogOpen by remember {
        mutableStateOf(false)
    }

    val openDialog = {
        isDialogOpen = true
    }

    val closeDialog = {
        isDialogOpen = false
    }

    val creditCardDetails = CreditCardDetails(
        balance = ownedCoins.getBalance(displayCurrencies[currentDisplayCurrencyIndex]),
        change = ownedCoins.getChange(),
        count = ownedCoins.size,
        displayCurrency = displayCurrencies[currentDisplayCurrencyIndex].currency,
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
                onChangeDisplayCurrency = {
                    currentDisplayCurrencyIndex = displayCurrencies.getNextIndex(currentDisplayCurrencyIndex)
                },
                creditCardDetails = creditCardDetails
            )
        }

        item{ Spacer(modifier = Modifier.height(32.dp)) }

        item{ HomeScreenActions(onTransferInClick, onTransferOutClick, openDialog) }

        if(isDialogOpen) {
            item {
                ConfirmDialog(
                    onDismissRequest = closeDialog,
                    onConfirm = {
                        isDialogOpen = false
                        onWipeWalletClick()
                    },
                    onDismiss = closeDialog
                )
            }
        }

        item{ Spacer(modifier = Modifier.height(32.dp)) }

        item{
            Text(
                text = "My Portfolio",
                style = MaterialTheme.typography.h2,
            )
        }

        item{ Spacer(modifier = Modifier.height(24.dp)) }

        items(items = ownedCoins){ ownedCoin ->
            OwnedCoinItem({},ownedCoin)
            Spacer(modifier = Modifier.height(16.dp))
        }


    }


}

@Preview
@Composable
fun HomeScreenSuccessWithDataPreview() {

    KryptoTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            HomeScreenSuccessWithData(FakeOwnedCoins, FakeDisplayCurrencies, {}, {}, {})
        }
    }

}
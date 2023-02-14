package com.ryankoech.krypto.feature_transaction.presentation.transaction.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ryankoech.krypto.common.core.ktx.isNull
import com.ryankoech.krypto.common.presentation.components.KryptoButton
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme
import com.ryankoech.krypto.common.presentation.theme.Red200
import com.ryankoech.krypto.common.presentation.theme.Red400
import com.ryankoech.krypto.common.presentation.util.DisplayCurrency
import com.ryankoech.krypto.common.presentation.util.ScreenState
import com.ryankoech.krypto.common.presentation.util.getFormattedBalance
import com.ryankoech.krypto.feature_coin_list.data.repository.FAKE_COIN_LIST
import com.ryankoech.krypto.feature_home.data.dto.owned_coin.OwnedCoinDto
import com.ryankoech.krypto.feature_home.data.repository.FakeOwnedCoins
import com.ryankoech.krypto.feature_transaction.R
import com.ryankoech.krypto.feature_transaction.data.dto.transaction_dto.TransactionDto
import com.ryankoech.krypto.feature_transaction.data.dto.transaction_dto.TransactionType
import com.ryankoech.krypto.feature_transaction.domain.entity.Coin
import com.ryankoech.krypto.feature_transaction.domain.entity.toCoinEntity
import java.util.*

@Composable
fun SellTransactionScreen(
    coin : Coin,
    onTransactionButtonClick : (TransactionDto) -> Unit,
    ownedCoinDto: OwnedCoinDto,
    screenState : ScreenState,
    modifier: Modifier = Modifier,
) {

    val context = LocalContext.current
    val textFieldTextStyle = MaterialTheme.typography.caption.copy(
        fontSize = 64.sp,
        textAlign = TextAlign.Center
    )
    val spacing = 24.dp
    var enteredQuanity by remember {
        mutableStateOf("")
    }
    var enterQuantityError by remember {
        mutableStateOf(false)
    }

    val onEnteredQuantityChange = onEnteredQuantityChange@{ text : String ->
        if(text.isEmpty()){
            enteredQuanity = text
            return@onEnteredQuantityChange
        }

        try {
            if (text.toDouble() > ownedCoinDto.amount) {
                enteredQuanity = ownedCoinDto.amount.toString()
                enterQuantityError = true
            } else {
                enteredQuanity = text
                enterQuantityError = false
            }
        }catch(_ : Throwable) {
            return@onEnteredQuantityChange
        }

    }

    Column(
        modifier = modifier
            .padding(12.dp)
            .fillMaxHeight()
    ) {

        Column(
            modifier = modifier
                .weight(1.0f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Row {
                Icon(
                    painter = painterResource(R.drawable.icon_exchange),
                    contentDescription = null
                )
                Text(
                    text = coin.symbol.uppercase(Locale.ROOT),
                    style = MaterialTheme.typography.body1.copy(
                        fontSize = 20.sp
                    )
                )
            }


            Spacer(Modifier.height(spacing))

            TextField(
                value = enteredQuanity,
                onValueChange = onEnteredQuantityChange,
                textStyle = textFieldTextStyle,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                placeholder = {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .alpha(0.4f),
                        text = "Quantity",
                        style = textFieldTextStyle,
                        color = Color.Black
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Unspecified,
                    unfocusedIndicatorColor = Color.Unspecified,
                    backgroundColor = Color.White
                ),
            )

            Spacer(Modifier.height(spacing))

            if(enterQuantityError){
                Text(
                    text = "You can not exceed your " + coin.name + " balance.",
                    style = MaterialTheme.typography.caption,
                    color = Red400
                )

                Spacer(Modifier.height(spacing / 2))
            }

            Text(
                text = "${getFormattedBalance(context, coin.price, DisplayCurrency.USD)} per coin.",
                style = MaterialTheme.typography.caption,
            )
        }

        KryptoButton(
            modifier = Modifier
                .fillMaxWidth(),
            text = "Sell " + coin.name,
            color = Red200,
            enabled = screenState == ScreenState.SUCCESS && enteredQuanity.toDoubleOrNull() != null,
            onClick = onClick@{

                if(enteredQuanity.toDoubleOrNull().isNull()){
                    return@onClick
                }

                val transaction = TransactionDto(
                    date = Calendar.getInstance().timeInMillis,
                    coinId = coin.id,
                    transactionType = TransactionType.SELL,
                    currentPrice = coin.price,
                    amount = enteredQuanity.toDouble()
                )

                onTransactionButtonClick(transaction)
            }
        )

    }

}

@Preview
@Composable
fun SellTransactionScreenPreview() {
    KryptoTheme {
        Surface {
            SellTransactionScreen(
                FAKE_COIN_LIST.toCoinEntity().first(),
                {},
                FakeOwnedCoins.first(),
                ScreenState.SUCCESS
            )
        }
    }
}
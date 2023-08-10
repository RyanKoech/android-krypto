package com.ryankoech.krypto.feature_transaction.presentation.transaction.components

import androidx.compose.foundation.background
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ryankoech.krypto.common.core.ktx.isNotNull
import com.ryankoech.krypto.common.core.ktx.isNull
import com.ryankoech.krypto.common.presentation.components.KryptoButton
import com.ryankoech.krypto.common.presentation.theme.Green200
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme
import com.ryankoech.krypto.common.presentation.util.DisplayCurrency
import com.ryankoech.krypto.common.presentation.util.KryptoPreview
import com.ryankoech.krypto.common.presentation.util.ScreenState
import com.ryankoech.krypto.common.presentation.util.getFormattedBalance
import com.ryankoech.krypto.feature_coin_list.data.repository.FAKE_COIN_LIST
import com.ryankoech.krypto.feature_transaction.R
import com.ryankoech.krypto.feature_transaction.data.dto.transaction_dto.TransactionDto
import com.ryankoech.krypto.feature_transaction.data.dto.transaction_dto.TransactionType
import com.ryankoech.krypto.feature_transaction.domain.entity.Coin
import com.ryankoech.krypto.feature_transaction.domain.entity.toCoinEntity
import java.util.*

@Composable
fun BuyTransactionScreen(
    coin : Coin,
    onTransactionButtonClick : (TransactionDto) -> Unit,
    screenState : ScreenState,
    modifier : Modifier = Modifier
) {

    val context = LocalContext.current
    val textFieldTextStyle = MaterialTheme.typography.caption.copy(
        fontSize = 64.sp,
        textAlign = TextAlign.Center
    )
    val spacing = 24.dp

    var enteredQuantity by remember {
        mutableStateOf("")
    }

    val onEnteredQuantityChange = onEnteredQuantityChange@{ text : String ->
        if(text.isEmpty()){
            enteredQuantity = text
            return@onEnteredQuantityChange
        }

        try {
            enteredQuantity = text
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
                value = enteredQuantity,
                onValueChange = onEnteredQuantityChange,
                textStyle = textFieldTextStyle,
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                placeholder = {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .alpha(0.4f),
                        text = stringResource(R.string.placeholder_transaction_amount),
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

            Text(
                text = stringResource(R.string.price_coin, getFormattedBalance(context, coin.price, DisplayCurrency.USD)),
                style = MaterialTheme.typography.caption,
            )

            if(enteredQuantity.toDoubleOrNull().isNotNull() && enteredQuantity.toDouble() > 0) {

                Spacer(Modifier.height(spacing))

                Box(
                    modifier = Modifier
                        .background(
                            color = Green200.copy(
                                alpha = 0.8F
                            ),
                            shape = MaterialTheme.shapes.small
                        ),
                ){
                    Text(
                        modifier = Modifier
                            .padding(
                                vertical = 4.dp,
                                horizontal = 6.dp
                            ),
                        text = stringResource(R.string.amount_tag_buying, getFormattedBalance(context, enteredQuantity.toDouble() * coin.price, DisplayCurrency.USD)),
                        style = MaterialTheme.typography.caption.copy(
                            fontSize = 12.sp
                        )
                    )
                }

            }
        }

        KryptoButton(
            modifier = Modifier
                .fillMaxWidth(),
            text = stringResource(R.string.button_label_buy, coin.name),
            enabled = screenState == ScreenState.SUCCESS && enteredQuantity.toDoubleOrNull() != null && enteredQuantity.toDouble() > 0,
            onClick = onClick@{

                if(enteredQuantity.toDoubleOrNull().isNull()){
                    return@onClick
                }

                val transaction = TransactionDto(
                    date = Calendar.getInstance().timeInMillis,
                    coinId = coin.id,
                    transactionType = TransactionType.BUY,
                    currentPrice = coin.price,
                    amount = enteredQuantity.toDouble()
                )

                onTransactionButtonClick(transaction)
            }
        )

    }

}

@KryptoPreview
@Composable
fun BuyTransactionScreenPreview() {
    KryptoTheme {
        Surface {
            BuyTransactionScreen(
                FAKE_COIN_LIST.toCoinEntity().first(),
                {},
                ScreenState.SUCCESS
            )
        }
    }
}
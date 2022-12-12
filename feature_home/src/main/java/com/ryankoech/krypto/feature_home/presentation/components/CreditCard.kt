package com.ryankoech.krypto.feature_home.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ryankoech.krypto.common.presentation.theme.Green500
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme
import com.ryankoech.krypto.common.presentation.theme.Red400
import com.ryankoech.krypto.feature_home.presentation.theme.creditCardBlue
import com.ryankoech.krypto.feature_home.presentation.theme.creditCardGreen
import com.ryankoech.krypto.feature_home.presentation.theme.creditCardYellow
import com.ryankoech.krypto.feature_home.presentation.theme.displayCurrencyBlue
import com.ryankoech.krypto.feature_home.presentation.util.CreditCardDetails
import com.ryankoech.krypto.feature_home.presentation.util.DisplayCurrency
import ke.co.sevenskies.feature_home.R
import java.text.DecimalFormat

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CreditCard(
    creditCardDetails: CreditCardDetails,
    onChangeDisplayCurrency : () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(LocalConfiguration.current.screenHeightDp.dp/4),
        elevation = 5.dp,
        shape = MaterialTheme.shapes.large
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            creditCardGreen,
                            creditCardGreen,
                            creditCardYellow,
                            creditCardBlue,
                        )
                    )
                ),
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize()
            ) {

                Row {
                    Text(
                        text = stringResource(R.string.credit_card_title),
                        style = MaterialTheme.typography.h4
                    )
                    Spacer(modifier = Modifier.weight(1.0f))
                    Card(
                        modifier = Modifier
                            .clickable{
                                onChangeDisplayCurrency()
                            },
                        border = BorderStroke(1.dp,Color.Black),
                        backgroundColor = displayCurrencyBlue,
                        elevation = 0.dp,
                        shape = MaterialTheme.shapes.small
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(start = 6.dp, top = 6.dp, end = 4.dp, bottom = 6.dp),
                            verticalAlignment = Alignment.Bottom
                        ) {
                            Text(
                                text = creditCardDetails.displayCurrency.toString(),
                                style = MaterialTheme.typography.h4,
                                fontSize = 12.sp
                            )
                            Icon(
                                modifier = Modifier
                                    .size(16.dp),
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = "",
                            )
                        }
                    }
                }

                Text(
                    text = getFormattedBalance(creditCardDetails.balance, creditCardDetails.displayCurrency),
                    style = MaterialTheme.typography.h1,
                    fontSize = 30.sp
                )

                Spacer(modifier = Modifier.weight(1.0f))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = pluralStringResource(R.plurals.credit_card_assets, creditCardDetails.count, creditCardDetails.count),
                        style = MaterialTheme.typography.h4,
                        fontSize = 12.sp
                    )
                    Icon(
                        modifier = Modifier
                            .size(32.dp)
                            .rotate(if (creditCardDetails.change < 0f) 0f else 180f),
                        imageVector = Icons.Filled.ArrowDropDown,
                        tint = getChangeColor(creditCardDetails.change),
                        contentDescription ="",
                    )
                    Text(
                        text = getFormattedChange(creditCardDetails.change),
                        style = MaterialTheme.typography.h4,
                        color = getChangeColor(creditCardDetails.change),
                    )
                }
            }
        }

    }
}



@Preview
@Composable
private fun CreditCardPreview() {
    KryptoTheme {
        Surface {
            CreditCard(
                CreditCardDetails(
                    balance = 12345.4545,
                    change = 2.54f,
                    count = 3,
                    displayCurrency = DisplayCurrency.USD,
                )
            ){
                println("Change  Currency")
            }
        }
    }
}

@Composable
private fun getFormattedBalance(balance : Double, displayCurrency: DisplayCurrency) : String {
    val df = DecimalFormat("#.##")
    val roundOffBalance = df.format(balance)
    return when (displayCurrency) {
        DisplayCurrency.USD -> stringResource(R.string.credit_card_balance, "$", roundOffBalance)
        else -> stringResource(R.string.credit_card_balance, displayCurrency.toString(), roundOffBalance)
    }
}

@Composable
private fun getFormattedChange(change : Float) : String {
    val df = DecimalFormat("#.##")
    val roundOffChange = df.format(change)
    return if(change == 0f)
        stringResource(R.string.credit_card_change, "0")
    else if (change < 0f)
        stringResource(R.string.credit_card_change,"", roundOffChange)
    else
        stringResource(R.string.credit_card_change, "+",roundOffChange)
}

private fun getChangeColor(change : Float) : Color {
    return  if (change < 0f) Red400 else Green500
}
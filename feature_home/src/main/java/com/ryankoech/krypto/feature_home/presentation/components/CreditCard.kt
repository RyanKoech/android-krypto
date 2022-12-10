package com.ryankoech.krypto.feature_home.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme
import com.ryankoech.krypto.feature_home.presentation.util.CreditCardDetails
import com.ryankoech.krypto.feature_home.presentation.util.DisplayCurrency

@Composable
fun CreditCard(
    creditCardDetails: CreditCardDetails,
    onChangeDisplayCurrency : () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        elevation = 5.dp,
        shape = RoundedCornerShape(20.dp)
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xff7cf057),
                            Color(0xff7cf057),
                            Color(0xffe5ff7b),
                            Color(0xff6bcfda),
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
                        text = "Estimated Balance",
                        style = MaterialTheme.typography.h4
                    )
                    Spacer(modifier = Modifier.weight(1.0f))
                    Card(
                        modifier = Modifier
                            .clickable{
                                onChangeDisplayCurrency()
                            },
                        border = BorderStroke(1.dp,Color.Black),
                        backgroundColor = Color(0xffc4f0bb),
                        elevation = 0.dp,
                        shape = RoundedCornerShape(100)
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(start = 6.dp, top = 6.dp, end = 4.dp, bottom = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
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
                    text = getFormatedBalance(creditCardDetails.balance, creditCardDetails.displayCurrency),
                    style = MaterialTheme.typography.h1,
                    fontSize = 30.sp
                )

                Spacer(modifier = Modifier.weight(1.0f))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${creditCardDetails.count} Assets",
                        style = MaterialTheme.typography.h4,
                        fontSize = 12.sp
                    )
                    Icon(
                        modifier = Modifier
                            .rotate(if (creditCardDetails.change < 0f) 0f else 180f),
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription =""
                    )
                    Text(
                        text = getFormattedChange(creditCardDetails.change),
                        style = MaterialTheme.typography.h4
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

private fun getFormatedBalance(balance : Double, displayCurrency: DisplayCurrency) : String {
    return when (displayCurrency) {
        DisplayCurrency.USD -> "$ ${String.format("%.2f", balance)}"
        DisplayCurrency.BTC -> "$displayCurrency ${String.format("%.2f", balance)}"
        DisplayCurrency.ETH -> "$displayCurrency ${String.format("%.2f", balance)}"
        DisplayCurrency.BNB -> "$displayCurrency ${String.format("%.2f", balance)}"
        DisplayCurrency.LTC -> "$displayCurrency ${String.format("%.2f", balance)}"
    }
}

private fun getFormattedChange(change : Float) : String {
    return if(change == 0f)
        "0%"
    else if (change < 0f)
        "${String.format("%.2f", change)}%"
    else
        "+${String.format("%.2f", change)}%"
}

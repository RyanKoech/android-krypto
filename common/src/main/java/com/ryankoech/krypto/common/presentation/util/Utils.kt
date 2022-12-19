package com.ryankoech.krypto.common.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.ryankoech.krypto.common.presentation.theme.Green500
import com.ryankoech.krypto.common.presentation.theme.Red400
import ke.co.sevenskies.common.R
import java.text.DecimalFormat

@Composable
fun getFormattedChange(change : Float) : String {
    val df = DecimalFormat("#.##")
    val roundOffChange = df.format(change)
    return if(change == 0f)
        stringResource(R.string.coin_value_change, "0")
    else if (change < 0f)
        stringResource(R.string.coin_value_change,"", roundOffChange)
    else
        stringResource(R.string.coin_value_change, "+",roundOffChange)
}

@Composable
fun getFormattedBalance(balance : Double, displayCurrency: DisplayCurrency) : String {
    val df = DecimalFormat("#.##")
    val roundOffBalance = df.format(balance)
    return when (displayCurrency) {
        DisplayCurrency.USD -> stringResource(R.string.coin_amount_balance, "$", roundOffBalance)
        else -> stringResource(R.string.coin_amount_balance, "$displayCurrency ", roundOffBalance)
    }
}

fun getChangeColor(change : Float) : Color {
    return  if (change < 0f) Red400 else Green500
}
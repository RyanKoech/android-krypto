package com.ryankoech.krypto.common.presentation.util

import android.content.Context
import androidx.compose.ui.graphics.Color
import com.ryankoech.krypto.common.presentation.theme.Green500
import com.ryankoech.krypto.common.presentation.theme.Red400
import com.ryankoech.krypto.common.R
import java.text.DecimalFormat

fun getFormattedChange(context: Context, change : Float) : String {
    val roundOffChange = getInTwoDecimalPlaces(change.toDouble())
    return if(change == 0f)
        context.getString(R.string.coin_value_change, "","0")
    else if (change < 0f)
        context.getString(R.string.coin_value_change,"", roundOffChange)
    else
        context.getString(R.string.coin_value_change, "+",roundOffChange)
}

fun getFormattedBalance(context : Context, balance : Double, displayCurrency: DisplayCurrency) : String {
    val roundOffBalance = getInTwoDecimalPlaces(balance)
    return when (displayCurrency) {
        DisplayCurrency.USD -> context.getString(R.string.coin_amount_balance, "$", roundOffBalance)
        else -> context.getString(R.string.coin_amount_balance, "$displayCurrency ", roundOffBalance)
    }
}

fun getChangeColor(change : Float) : Color {
    return  if (change < 0f) Red400 else Green500
}

fun getInTwoDecimalPlaces(value : Double) : String{
    val df = DecimalFormat("#.##")
    return df.format(value)
}
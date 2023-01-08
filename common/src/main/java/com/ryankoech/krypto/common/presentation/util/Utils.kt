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
fun getFormattedMarketCap(context: Context, value : Long) : String {
    val million = 1_000_000
    val billion = 1_000_000_000
    val trillion = 1_000_000_000_000
    val quadrillion = 1_000_000_000_000_000
    val quintillion = 1_000_000_000_000_000_000

    return if(value < million) {
        val df = DecimalFormat("#,###.##")
        context.getString(R.string.coin_market_cap, df.format(value), "")
    }else if(value < billion){
        context.getString(
            R.string.coin_market_cap,
            getInFourDecimalPlaces(value.toDouble() / million.toDouble()),
            "M"
        )
    }else if(value < trillion){
        context.getString(
            R.string.coin_market_cap,
            getInFourDecimalPlaces(value.toDouble() / billion.toDouble()),
            "Bn"
        )
    }else if(value < quadrillion){
        context.getString(
            R.string.coin_market_cap,
            getInFourDecimalPlaces(value.toDouble() / trillion.toDouble()),
            "Tr"
        )
    }else if(value < quintillion){
        context.getString(
            R.string.coin_market_cap,
            getInFourDecimalPlaces(value.toDouble() / quadrillion.toDouble()),
            "Tr"
        )
    }else {
        "Over $1 Qnt"
    }
}

fun getInFourDecimalPlaces(value : Double) : String{
    val df = DecimalFormat("#,##0.0000")
    return df.format(value)
}
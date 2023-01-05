package com.ryankoech.krypto.feature_home.data.dto.owned_coin

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ryankoech.krypto.feature_home.data.dto.display_currency.DisplayCurrencyDto

const val OWNED_COIN_DTO_TABLENAME = "owned_coin_table"

@Entity(tableName = OWNED_COIN_DTO_TABLENAME)
data class OwnedCoinDto(
    @PrimaryKey(autoGenerate = false)
    val id : String,
    val symbol : String,
    val value : Double,
    val change : Float,
    val amount : Double,
    val icon : String,
)

fun List<OwnedCoinDto>.getBalance(displayCurrency: DisplayCurrencyDto) : Double {

    var balance = 0.0

    forEach { coin ->
        val total = coin.amount * coin.value
        balance+=total
    }

    return balance / displayCurrency.value
}

fun List<OwnedCoinDto>.getChange() : Float {

    var change = 0f

    forEach { coin ->
        change+=coin.change
    }

    return change
}
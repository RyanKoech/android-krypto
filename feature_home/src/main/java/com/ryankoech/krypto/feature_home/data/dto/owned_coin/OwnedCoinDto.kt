package com.ryankoech.krypto.feature_home.data.dto.owned_coin

import androidx.room.Entity
import androidx.room.PrimaryKey

const val OWNED_COIN_DTO_TABLENAME = "owned_coin_table"

@Entity(tableName = OWNED_COIN_DTO_TABLENAME)
data class OwnedCoinDto(
    @PrimaryKey(autoGenerate = false)
    val id : String,
    val symbol : String,
    val value : Float,
    val change : Double,
    val amount : Float,
    val icon : String,
)

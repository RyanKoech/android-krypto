package com.ryankoech.krypto.feature_home.data.dto.owned_coin

import androidx.room.Entity
import androidx.room.PrimaryKey

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

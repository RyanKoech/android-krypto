package com.ryankoech.krypto.feature_coin_details.domain.entity

import com.ryankoech.krypto.feature_transaction.domain.entity.Coin as TransactionCoin
import com.ryankoech.krypto.feature_coin_list.domain.entity.Coin as CoinListCoin

fun CoinListCoin.toTransactionCoin() : TransactionCoin =
    TransactionCoin(
        id = id,
        symbol = symbol,
        name = name,
        image = image,
        price = price
    )
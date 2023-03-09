package com.ryankoech.krypto.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ryankoech.krypto.feature_transaction.domain.entity.Coin as TransactionCoin
import com.ryankoech.krypto.feature_coin_list.domain.entity.Coin as CoinListCoin
import javax.inject.Inject

class MainViewModel @Inject constructor() : ViewModel(){

    var transactionCoin by mutableStateOf<TransactionCoin?>(null)
        private set

    fun addTransactionCoin(coin: TransactionCoin){
        transactionCoin = coin
    }
}
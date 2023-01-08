package com.ryankoech.krypto.feature_coin_list.presentation.viewstate

import com.ryankoech.krypto.common.presentation.util.ScreenState
import com.ryankoech.krypto.feature_coin_list.domain.entity.Coin

data class CoinListScreenviewState(
    val screenState: ScreenState = ScreenState.LOADING,
    val coins : List<Coin> = listOf(),
)

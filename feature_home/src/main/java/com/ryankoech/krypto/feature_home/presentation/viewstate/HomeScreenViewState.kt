package com.ryankoech.krypto.feature_home.presentation.viewstate

import com.ryankoech.krypto.common.presentation.util.ScreenState
import com.ryankoech.krypto.feature_home.data.dto.owned_coin.OwnedCoinDto

data class HomeScreenViewState(
    val screenState: ScreenState = ScreenState.LOADING,
    val ownedCoins : List<OwnedCoinDto> = listOf()
)
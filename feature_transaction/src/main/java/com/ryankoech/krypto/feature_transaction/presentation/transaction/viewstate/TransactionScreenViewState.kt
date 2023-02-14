package com.ryankoech.krypto.feature_transaction.presentation.transaction.viewstate

import com.ryankoech.krypto.common.presentation.util.ScreenState
import com.ryankoech.krypto.feature_home.data.dto.owned_coin.OwnedCoinDto

data class TransactionScreenViewState(
    val screenState: ScreenState = ScreenState.LOADING,
    val ownedCoin : OwnedCoinDto = OwnedCoinDto("", "", 0.0, 0.0F, 0.0, ""),
    val backToHome : Boolean = false,
)

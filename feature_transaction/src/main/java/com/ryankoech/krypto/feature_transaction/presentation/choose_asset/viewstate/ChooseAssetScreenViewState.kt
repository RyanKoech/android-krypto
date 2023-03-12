package com.ryankoech.krypto.feature_transaction.presentation.choose_asset.viewstate

import com.ryankoech.krypto.common.presentation.util.ScreenState
import com.ryankoech.krypto.feature_transaction.domain.entity.Coin

data class ChooseAssetScreenViewState(
    val screenState: ScreenState = ScreenState.LOADING,
    val coins : List<Coin> = listOf(),
)

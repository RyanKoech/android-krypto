package com.ryankoech.krypto.feature_coin_details.presentation.viewstate

import com.ryankoech.krypto.common.presentation.util.ScreenState
import com.ryankoech.krypto.feature_coin_details.domain.entity.MarketChartEntity
import com.ryankoech.krypto.feature_transaction.data.dto.transaction_dto.TransactionDto

data class CoinDetailsScreenViewState(
    val screenState: ScreenState = ScreenState.LOADING,
    val dayMarketChart : List<MarketChartEntity> = listOf(),
    val threeMonthMarketChart : List<MarketChartEntity> = listOf(),
    val yearMarketChart : List<MarketChartEntity> = listOf(),
    val transactions : List<TransactionDto> = listOf(),
)

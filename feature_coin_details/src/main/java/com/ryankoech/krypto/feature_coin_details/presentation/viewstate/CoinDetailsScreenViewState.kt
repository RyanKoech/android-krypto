package com.ryankoech.krypto.feature_coin_details.presentation.viewstate

import com.ryankoech.krypto.common.presentation.util.ScreenState
import com.ryankoech.krypto.feature_coin_details.domain.entity.MarketChartEntity
import com.ryankoech.krypto.feature_transaction.data.dto.transaction_dto.TransactionDto

data class CoinDetailsScreenViewState(
    private val screenState: ScreenState = ScreenState.LOADING,
    private val dayMarketChart : List<MarketChartEntity> = listOf(),
    private val threeMonthMarketChart : List<MarketChartEntity> = listOf(),
    private val yearMarketChart : List<MarketChartEntity> = listOf(),
    private val transactions : List<TransactionDto> = listOf(),
)

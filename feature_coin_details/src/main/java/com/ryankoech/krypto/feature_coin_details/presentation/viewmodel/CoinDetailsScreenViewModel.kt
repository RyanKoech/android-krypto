package com.ryankoech.krypto.feature_coin_details.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ryankoech.krypto.common.core.util.Resource
import com.ryankoech.krypto.common.presentation.util.ScreenState
import com.ryankoech.krypto.feature_coin_details.domain.usecase.GetCoinMarketChartUseCase
import com.ryankoech.krypto.feature_coin_details.presentation.viewstate.CoinDetailsScreenViewState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class CoinDetailsScreenViewModel @Inject constructor(
    private val getCoinMarketChartUseCase: GetCoinMarketChartUseCase
) : ViewModel(){

    private val _viewState = mutableStateOf(CoinDetailsScreenViewState())
    private val viewState : State<CoinDetailsScreenViewState> = _viewState

    fun getCoinDetails(coinId :String) {

        getCoinMarketChartUseCase(coinId)
            .onEach { res ->
                when(res){
                    is Resource.Error -> {
                        _viewState.value = _viewState.value.copy(
                            screenState = ScreenState.ERROR,
                        )
                    }
                    is Resource.Loading -> {
                        _viewState.value = _viewState.value.copy(
                            screenState = ScreenState.LOADING,
                        )
                    }
                    is Resource.Success -> {
                        _viewState.value = _viewState.value.copy(
                            screenState = ScreenState.SUCCESS,
                            dayMarketChart = res.data!![0],
                            threeMonthMarketChart =  res.data!![1],
                            yearMarketChart =  res.data!![2]
                        )
                    }
                }

            }.launchIn(viewModelScope)

    }

}
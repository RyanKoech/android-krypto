package com.ryankoech.krypto.feature_coin_list.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ryankoech.krypto.common.core.util.Resource
import com.ryankoech.krypto.common.presentation.util.ScreenState
import com.ryankoech.krypto.feature_coin_list.domain.entity.Order
import com.ryankoech.krypto.feature_coin_list.domain.entity.SortCoinBy
import com.ryankoech.krypto.feature_coin_list.domain.entity.SortInfo
import com.ryankoech.krypto.feature_coin_list.domain.usecase.GetCoinsUseCase
import com.ryankoech.krypto.feature_coin_list.domain.usecase.GetLocalCoinsUseCase
import com.ryankoech.krypto.feature_coin_list.presentation.viewstate.CoinListScreenviewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

val DEFAULT_SORT_INFO = SortInfo(
    sortBy = SortCoinBy.MARKET_CAP,
    order = Order.DESC
)

@HiltViewModel
class CoinListScreenViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase,
    private val getLocalCoinsUseCase : GetLocalCoinsUseCase
) : ViewModel() {

    private val _viewState = mutableStateOf(CoinListScreenviewState())
    val viewState : State<CoinListScreenviewState> = _viewState

    init {
        getCoins(DEFAULT_SORT_INFO, "")
    }

    fun getCoins(sortInfo: SortInfo, filterString : String) {

        getCoinsUseCase(sortInfo, filterString)
            .onEach { res ->
                when(res){
                    is Resource.Error -> {
                        getLocalCoins()
                    }
                    is Resource.Loading -> {
                        _viewState.value = _viewState.value.copy(
                            screenState = ScreenState.LOADING
                        )
                    }
                    is Resource.Success -> {
                        _viewState.value = _viewState.value.copy(
                            screenState = ScreenState.SUCCESS,
                            coins = res.data!!
                        )
                    }
                    else -> {
                        _viewState.value = _viewState.value.copy(
                            screenState = ScreenState.ERROR
                        )
                    }
                }

            }.launchIn(viewModelScope)

    }


    private fun getLocalCoins() {

        getLocalCoinsUseCase()
            .onEach { res ->
                when(res) {
                    is Resource.Error -> {
                        _viewState.value = _viewState.value.copy(
                            screenState = ScreenState.ERROR
                        )
                    }
                    is Resource.Loading -> {
                        _viewState.value = _viewState.value.copy(
                            screenState = ScreenState.LOADING
                        )
                    }
                    is Resource.Success -> {
                        if(res.data.isNullOrEmpty()){
                            _viewState.value = _viewState.value.copy(
                                screenState = ScreenState.ERROR
                            )
                        }else{
                            _viewState.value = _viewState.value.copy(
                                screenState = ScreenState.SUCCESS,
                                coins = res.data!!
                            )
                        }
                    }
                    else -> {
                        _viewState.value = _viewState.value.copy(
                            screenState = ScreenState.ERROR
                        )
                    }
                }
            }.launchIn(viewModelScope)

    }

}
package com.ryankoech.krypto.feature_coin_details.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ryankoech.krypto.common.core.util.Resource
import com.ryankoech.krypto.common.presentation.util.ScreenState
import com.ryankoech.krypto.feature_coin_details.domain.usecase.GetCoinMarketChartUseCase
import com.ryankoech.krypto.feature_coin_details.presentation.viewstate.CoinDetailsScreenViewState
import com.ryankoech.krypto.feature_transaction.domain.usecase.DeleteCoinTransactionUseCase
import com.ryankoech.krypto.feature_transaction.domain.usecase.GetCoinTransactionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailsScreenViewModel @Inject constructor(
    private val getCoinMarketChartUseCase: GetCoinMarketChartUseCase,
    private val getCoinTransactionsUseCase: GetCoinTransactionsUseCase,
    private val deleteCoinTransactionUseCase: DeleteCoinTransactionUseCase,
) : ViewModel(){

    private val _viewState = mutableStateOf(CoinDetailsScreenViewState())
    val viewState : State<CoinDetailsScreenViewState> = _viewState
    private val _message = MutableSharedFlow<String>()
    val message : SharedFlow<String> get() = _message

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

    fun getTransactions(coinId: String) {

        getCoinTransactionsUseCase(coinId)
            .onEach { res ->
                   
                when(res){
                    is Resource.Error -> {
                        _message.emit("An error occurred while fetching transactions.")
                    }
                    is Resource.Loading -> {
                        // Do nothing
                    }
                    is Resource.Success -> {
                        _viewState.value = _viewState.value.copy(
                            transactions = res.data!!
                        )
                    }
                }


            }.launchIn(viewModelScope)

    }

    fun deleteCoinTransaction(date : Long) {

        deleteCoinTransactionUseCase(date)
            .onEach { res ->
                when(res){
                    is Resource.Error -> {
                        _message.emit("An error occurred while deleting the transaction.")
                    }
                    is Resource.Loading -> {
                        // Do nothing
                    }
                    is Resource.Success -> {
                        _viewState.value = _viewState.value.copy(
                            transactions = _viewState.value.transactions.filter { it.date != date }
                        )
                    }
                }
            }

    }

}
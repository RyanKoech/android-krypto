package com.ryankoech.krypto.feature_home.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ryankoech.krypto.common.core.util.Resource
import com.ryankoech.krypto.common.presentation.util.ScreenState
import com.ryankoech.krypto.feature_home.domain.usecase.GetOwnedCoinsUseCase
import com.ryankoech.krypto.feature_home.domain.usecase.SaveOwnedCoinUseCase
import com.ryankoech.krypto.feature_home.presentation.viewstate.HomeScreenViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getOwnedCoinsUseCase : GetOwnedCoinsUseCase,
    private val saveOwnedCoinUseCase: SaveOwnedCoinUseCase
) : ViewModel() {

    private val _viewState = mutableStateOf(HomeScreenViewState())
    val viewState : State<HomeScreenViewState> = _viewState

    init {
        getOwnedCoins()
    }

    private fun getOwnedCoins() {

        getOwnedCoinsUseCase()
            .onEach { res ->
                when(res) {
                    is Resource.Error -> {
                        screenError()
                    }
                    is Resource.Loading -> {
                        screenLoading()
                    }
                    is Resource.Success -> {
                        _viewState.value = _viewState.value.copy(
                            ownedCoins = res.data ?: emptyList()
                        )
                        screenSuccess()
                    }
                    else -> {
                        screenError()
                    }
                }
            }.launchIn(viewModelScope)

    }


    private fun screenLoading() {
        println("Loading")
        _viewState.value = _viewState.value.copy(
            screenState = ScreenState.LOADING
        )
    }

    private fun screenError() {
        println("Error")
        _viewState.value = _viewState.value.copy(
            screenState = ScreenState.ERROR
        )
    }

    private fun screenSuccess() {
        println("Success")
        _viewState.value = _viewState.value.copy(
            screenState = ScreenState.SUCCESS
        )
    }

}
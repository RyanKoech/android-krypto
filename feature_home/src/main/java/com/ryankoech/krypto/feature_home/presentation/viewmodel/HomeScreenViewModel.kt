package com.ryankoech.krypto.feature_home.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ryankoech.krypto.common.core.util.Resource
import com.ryankoech.krypto.common.presentation.util.ScreenState
import com.ryankoech.krypto.feature_coin_list.domain.usecase.GetDisplayCurrencyDataUseCase
import com.ryankoech.krypto.feature_home.domain.usecase.GetOwnedCoinsUseCase
import com.ryankoech.krypto.feature_home.domain.usecase.WipeDatabaseUseCase
import com.ryankoech.krypto.feature_home.presentation.viewstate.HomeScreenViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getOwnedCoinsUseCase : GetOwnedCoinsUseCase,
    private val getDisplayCurrencyDataUseCase: GetDisplayCurrencyDataUseCase,
    private val wipeDatabaseUseCase: WipeDatabaseUseCase,
) : ViewModel() {

    private val _viewState = mutableStateOf(HomeScreenViewState())
    val viewState : State<HomeScreenViewState> = _viewState
    private val _message = MutableSharedFlow<String>()
    val message : SharedFlow<String> get() = _message

    init {
        getDisplayCurrencies()
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

    private fun getDisplayCurrencies() {

        getDisplayCurrencyDataUseCase()
            .onEach { res ->
                when(res) {
                    is Resource.Error -> {
                        _viewState.value = _viewState.value.copy(
                            displayCurrencies = res.data!!
                        )
                    }
                    is Resource.Success -> {
                        _viewState.value = _viewState.value.copy(
                            displayCurrencies = res.data!!
                        )
                    }
                    else -> {}
                }
            }.launchIn(viewModelScope)

    }

    fun wipeOwnedCoinsDatabase() {

        wipeDatabaseUseCase()
            .onEach { res ->
                when (res) {
                    is Resource.Error -> {
                        // Emit Error to UI
                        _message.emit("An error occurred while wiping portfolio.")
                    }
                    is Resource.Success -> {
                        _viewState.value = _viewState.value.copy(
                            ownedCoins = emptyList()
                        )
                    }
                    else -> {}
                }
            }.launchIn(viewModelScope)


    }

    private fun screenLoading() {
        _viewState.value = _viewState.value.copy(
            screenState = ScreenState.LOADING
        )
    }

    private fun screenError() {
        _viewState.value = _viewState.value.copy(
            screenState = ScreenState.ERROR
        )
    }

    private fun screenSuccess() {
        _viewState.value = _viewState.value.copy(
            screenState = ScreenState.SUCCESS
        )
    }

}
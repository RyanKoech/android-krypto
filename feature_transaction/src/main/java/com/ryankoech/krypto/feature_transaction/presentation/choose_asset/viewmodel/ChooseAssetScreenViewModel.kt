package com.ryankoech.krypto.feature_transaction.presentation.choose_asset.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ryankoech.krypto.common.core.util.Resource
import com.ryankoech.krypto.common.presentation.util.ScreenState
import com.ryankoech.krypto.feature_transaction.domain.usecase.GetCoinListUseCase
import com.ryankoech.krypto.feature_transaction.presentation.choose_asset.viewstate.ChooseAssetScreenViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ChooseAssetScreenViewModel @Inject constructor(
    private val getCoinListUseCase: GetCoinListUseCase
): ViewModel() {

    private val _viewState = mutableStateOf(ChooseAssetScreenViewState())
    val viewState : State<ChooseAssetScreenViewState> = _viewState

    init {
        getCoinList()
    }

    private fun getCoinList() {
        getCoinListUseCase()
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
                        _viewState.value = _viewState.value.copy(
                            screenState = ScreenState.SUCCESS,
                            coins = res.data!!
                        )

                    }
                }
            }.launchIn(viewModelScope)
    }

}
package com.ryankoech.krypto.feature_coin_list.presentation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme
import com.ryankoech.krypto.common.presentation.util.ScreenState
import com.ryankoech.krypto.feature_coin_list.data.dto.toCoinEntity
import com.ryankoech.krypto.feature_coin_list.data.repository.FAKE_COIN_LIST
import com.ryankoech.krypto.feature_coin_list.domain.usecase.GetCoinsUseCase
import com.ryankoech.krypto.feature_coin_list.presentation.components.success.TEST_TAG_NO_COINS_FOUND
import com.ryankoech.krypto.feature_coin_list.presentation.viewmodel.CoinListScreenViewModel
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class CoinListScreenSuccessTest {

    @get:Rule
    val composeTest = createComposeRule()

    @Test
    fun receivesEmptyCoinList_displayNoCoinFallback() {

        composeTest.setContent {
            KryptoTheme {
                CoinListScreenSuccess(
                    coinItemOnClick = {},
                    screenState = ScreenState.SUCCESS,
                    editSortInfo = {},
                    sortInfoState = CoinListScreenViewModel.DEFAULT_SORT_INFO,
                    coins = listOf(),
                    filterStringState = "",
                    editFilterStringState = {}
                )
            }
        }

        composeTest.onNodeWithTag(TEST_TAG_NO_COINS_FOUND).assertIsDisplayed()

    }

    @Test
    fun receivesCoinList_displayCoinsList() {

        composeTest.setContent {
            KryptoTheme {
                CoinListScreenSuccess(
                    coinItemOnClick = {},
                    screenState = ScreenState.SUCCESS,
                    editSortInfo = {},
                    sortInfoState = CoinListScreenViewModel.DEFAULT_SORT_INFO,
                    coins = FAKE_COIN_LIST.toCoinEntity(),
                    filterStringState = "",
                    editFilterStringState = {}
                )
            }
        }

        composeTest.onNodeWithTag(TEST_TAG_COIN_LIST_SCREEN_SUCCESS_COINS_LAZY_COLUMN).assertIsDisplayed()
        composeTest.onNodeWithText(FAKE_COIN_LIST.toCoinEntity().first().name).assertIsDisplayed()

    }

}
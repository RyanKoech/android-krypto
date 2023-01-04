package com.ryankoech.krypto.feature_home.presentation

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme
import com.ryankoech.krypto.feature_home.data.dto.display_currency.DisplayCurrencyDto
import com.ryankoech.krypto.feature_home.data.repository.FakeDisplayCurrencies
import com.ryankoech.krypto.feature_home.data.repository.FakeOwnedCoins
import com.ryankoech.krypto.feature_home.data.repository.FakeOwnedCoinsRepositoryImpl
import com.ryankoech.krypto.feature_home.domain.usecase.GetDisplayCurrencyDataUseCase
import com.ryankoech.krypto.feature_home.presentation.components.success.TEST_TAG_HOME_SCREEN_SUCCESS_NO_DATA
import com.ryankoech.krypto.feature_home.presentation.components.success.TEST_TAG_HOME_SCREEN_SUCCESS_WITH_DATA
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeScreenSuccessTest {

    private lateinit var displayCurrencyList: List<DisplayCurrencyDto>

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {

        displayCurrencyList = FakeDisplayCurrencies
    }


    @Test
    fun providedEmptyList_displayHomeScreenWithoutData() {

        composeTestRule.setContent {
            KryptoTheme {
                HomeScreenSuccess(
                    ownedCoins = listOf(),
                    displayCurrencies = displayCurrencyList,
                    onTransferInClick = { },
                    onTransferOutClick = { },
                    onWipeWalletClick = { }
                )
            }
        }

        composeTestRule.onNodeWithTag(TEST_TAG_HOME_SCREEN_SUCCESS_NO_DATA)
    }

    @Test
    fun providedPopulatedList_displayHomeScreenWithData() {

        composeTestRule.setContent {
            KryptoTheme {
                HomeScreenSuccess(
                    ownedCoins = FakeOwnedCoins,
                    displayCurrencies = displayCurrencyList,
                    onTransferInClick = { },
                    onTransferOutClick = { },
                    onWipeWalletClick = { }
                )
            }
        }

        composeTestRule.onNodeWithTag(TEST_TAG_HOME_SCREEN_SUCCESS_WITH_DATA)
    }
}
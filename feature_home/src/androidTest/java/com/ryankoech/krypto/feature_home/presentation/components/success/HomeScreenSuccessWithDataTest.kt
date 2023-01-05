package com.ryankoech.krypto.feature_home.presentation.components.success

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme
import com.ryankoech.krypto.common.presentation.util.DisplayCurrency
import com.ryankoech.krypto.feature_home.data.dto.display_currency.DisplayCurrencyDto
import com.ryankoech.krypto.feature_home.data.dto.owned_coin.OwnedCoinDto
import com.ryankoech.krypto.feature_home.data.dto.owned_coin.getBalance
import com.ryankoech.krypto.common.R as commonR
import com.ryankoech.krypto.feature_home.R

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.text.DecimalFormat

class HomeScreenSuccessWithDataTest1 {

    private lateinit var ownedCoins : List<OwnedCoinDto>

    private lateinit var displayCurrencies : List<DisplayCurrencyDto>

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setUp() {

        ownedCoins = listOf(
            OwnedCoinDto(
                id = "bitcoin",
                symbol = "btc",
                amount = 1.0,
                value = 1000.00,
                change = -3.60164f,
                icon = "https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1547033579"
            ),
            OwnedCoinDto(
                id = "ethereum",
                symbol = "eth",
                amount = 1.0,
                value = 100.00,
                change = 1.164f,
                icon = "https://assets.coingecko.com/coins/images/279/large/ethereum.png?1595348880"
            )
        )

        displayCurrencies = listOf(
            DisplayCurrencyDto(DisplayCurrency.LTC, 1.0),
            DisplayCurrencyDto(DisplayCurrency.BNB, 10.0),
        )

        composeTestRule.setContent {
            KryptoTheme {
                HomeScreenSuccessWithData(
                    ownedCoins = ownedCoins,
                    displayCurrencies = displayCurrencies,
                    onTransferInClick = {},
                    onTransferOutClick = {},
                    onWipeWalletClick = {}
                )
            }
        }

    }

    @Test
    fun clickOnChangeDisplayCurrencyDropdown_changeDisplayValueAndSymbol() {

        val index = 0
        val expectedTextBeforeClick = getCreditCardAmountText(index)
        composeTestRule.onNodeWithText(expectedTextBeforeClick).assertExists()

        composeTestRule.onNode(
            hasText(displayCurrencies[index].currency.toString())
                    and
                    hasClickAction()
        ).performClick()

        composeTestRule.onNode(
            hasText(displayCurrencies[index+1].currency.toString())
                    and
                    hasClickAction()
        ).assertExists()

        val expectedTextAfterClick = getCreditCardAmountText(index+1)
        composeTestRule.onNodeWithText(expectedTextAfterClick).assertExists()

    }

    @Test
    fun clickWipeWallet_openConfirmDialog() {

        composeTestRule.onNodeWithTag(HOME_SCREEN_ACTION_ITEM_WIPE_WALLET).onChildAt(0).performClick()

        composeTestRule.onNodeWithTag(TEST_TAG_HOME_SCREEN_SUCCESS_WITH_DATA_CONFIRM_DIALOG).assertIsDisplayed()

    }

    @Test
    fun clickCancelWipeWallet_closeConfirmDialog() {

        composeTestRule.onNodeWithTag(HOME_SCREEN_ACTION_ITEM_WIPE_WALLET).onChildAt(0).performClick()

        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.button_text_wipe_no)).performClick()

        composeTestRule.onNodeWithTag(TEST_TAG_HOME_SCREEN_SUCCESS_WITH_DATA_CONFIRM_DIALOG).assertDoesNotExist()

    }

    private fun getCreditCardAmountText(index: Int) : String {
        val df = DecimalFormat("#.##")
        val roundOffBalance = df.format(ownedCoins.getBalance(displayCurrencies[index]))
        return composeTestRule.activity.getString(commonR.string.coin_amount_balance, "${displayCurrencies[index].currency} ", roundOffBalance)
    }

}
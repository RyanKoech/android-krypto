package com.ryankoech.krypto.feature_home.presentation

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.ryankoech.krypto.common.core.util.Resource
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme
import com.ryankoech.krypto.feature_home.R
import com.ryankoech.krypto.feature_home.core.util.EXCEPTION_MESSAGE
import com.ryankoech.krypto.feature_home.data.dto.owned_coin.OwnedCoinDto
import com.ryankoech.krypto.feature_home.data.repository.FakeOwnedCoinsRepositoryImpl
import com.ryankoech.krypto.feature_coin_list.domain.usecase.GetDisplayCurrencyDataUseCase
import com.ryankoech.krypto.feature_home.domain.usecase.GetOwnedCoinsUseCase
import com.ryankoech.krypto.feature_home.domain.usecase.WipeDatabaseUseCase
import com.ryankoech.krypto.feature_home.presentation.components.success.HOME_SCREEN_ACTION_ITEM_WIPE_WALLET
import com.ryankoech.krypto.feature_home.presentation.viewmodel.HomeScreenViewModel
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    private lateinit var wipeDatabaseUseCase: WipeDatabaseUseCase

    @MockK
    private lateinit var getOwnedCoinsUseCase: GetOwnedCoinsUseCase

    private lateinit var getDisplayCurrencyDataUseCase: GetDisplayCurrencyDataUseCase

    private lateinit var ownedCoinFlow : Flow<Resource<List<OwnedCoinDto>>>

    private lateinit var viewModel : HomeScreenViewModel

    @Before
    fun setUp(){
        getDisplayCurrencyDataUseCase = GetDisplayCurrencyDataUseCase(FakeOwnedCoinsRepositoryImpl())
    }

    @Test
    fun fetchOwnedCoinsError_displayErrorScreen() {
        ownedCoinFlow = flow{
            emit(Resource.Error(EXCEPTION_MESSAGE))
        }

        coEvery { getOwnedCoinsUseCase() } returns ownedCoinFlow

        viewModel = HomeScreenViewModel(
            getOwnedCoinsUseCase,
            getDisplayCurrencyDataUseCase,
            wipeDatabaseUseCase
        )

        composeTestRule.setContent {
            KryptoTheme {
                HomeScreen(onTransferInClick = {}, onTransferOutClick = {}, viewModel)
            }
        }

        composeTestRule.onNodeWithTag(TEST_TAG_HOME_SCREEN_ERROR).assertIsDisplayed()
    }

    @Test
    fun fetchOwnedCoinsLoading_displayLoadingScreen(){
        ownedCoinFlow = flow {
            emit(Resource.Loading())
        }

        coEvery { getOwnedCoinsUseCase() } returns ownedCoinFlow

        viewModel = HomeScreenViewModel(
            getOwnedCoinsUseCase,
            getDisplayCurrencyDataUseCase,
            wipeDatabaseUseCase
        )

        composeTestRule.setContent {
            KryptoTheme {
                HomeScreen(onTransferInClick = {}, onTransferOutClick = {}, viewModel)
            }
        }
        composeTestRule.onNodeWithTag(TEST_TAG_HOME_SCREEN_LOADING).assertIsDisplayed()
    }

    @Test
    fun fetchOwnedCoinsSuccess_displaySuccessScreen(){
        ownedCoinFlow = flow {
            emit(Resource.Success(listOf()))
        }

        coEvery { getOwnedCoinsUseCase() } returns ownedCoinFlow

        viewModel = HomeScreenViewModel(
            getOwnedCoinsUseCase,
            getDisplayCurrencyDataUseCase,
            wipeDatabaseUseCase
        )

        composeTestRule.setContent {
            KryptoTheme {
                HomeScreen(onTransferInClick = {}, onTransferOutClick = {}, viewModel)
            }
        }
        composeTestRule.onNodeWithTag(TEST_TAG_HOME_SCREEN_SUCCESS).assertIsDisplayed()
    }

    @Test
    fun clickConfirmWipeWallet_closeConfirmDialog() {
        getOwnedCoinsUseCase = GetOwnedCoinsUseCase(FakeOwnedCoinsRepositoryImpl())
        wipeDatabaseUseCase = WipeDatabaseUseCase(FakeOwnedCoinsRepositoryImpl())

        viewModel = HomeScreenViewModel(
            getOwnedCoinsUseCase,
            getDisplayCurrencyDataUseCase,
            wipeDatabaseUseCase
        )

        composeTestRule.setContent {
            KryptoTheme {
                HomeScreen(onTransferInClick = {}, onTransferOutClick = {}, viewModel)
            }
        }

        composeTestRule.onNodeWithTag(HOME_SCREEN_ACTION_ITEM_WIPE_WALLET).onChildAt(0).performClick()

        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.button_text_wipe_yes)).performClick()

        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.home_screen_success_not_data_message)).assertIsDisplayed()

    }

}
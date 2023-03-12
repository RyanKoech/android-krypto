package com.ryankoech.krypto.feature_coin_list.presentation

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.ryankoech.krypto.common.core.util.Resource
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme
import com.ryankoech.krypto.feature_coin_list.core.util.Util.Companion.MOCK_EXCEPTION_MESSAGE
import com.ryankoech.krypto.feature_coin_list.domain.entity.Coin
import com.ryankoech.krypto.feature_coin_list.domain.usecase.GetCoinsUseCase
import com.ryankoech.krypto.feature_coin_list.presentation.components.success.TEST_TAG_COIN_LIST_LOADING
import com.ryankoech.krypto.feature_coin_list.presentation.viewmodel.CoinListScreenViewModel
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

import org.junit.Rule
import org.junit.Test

class CoinListScreenTest {


    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    private lateinit var getCoinsUseCase: GetCoinsUseCase

    private lateinit var coinFlow : Flow<Resource<List<Coin>>>

    private lateinit var viewModel: CoinListScreenViewModel

    @Test
    fun fetchCoinsError_displayErrorScreen() {
        coinFlow = flow {
            emit(Resource.Error(MOCK_EXCEPTION_MESSAGE))
        }

        coEvery { getCoinsUseCase(any()) } returns coinFlow
        viewModel = CoinListScreenViewModel(getCoinsUseCase)

        composeTestRule.setContent {
            KryptoTheme {
                CoinListScreen(
                    coinItemOnClick = {},
                    viewModel = viewModel
                )
            }
        }

        composeTestRule.onNodeWithTag(TEST_TAG_COIN_LIST_SCREEN_ERROR).assertIsDisplayed()
    }

    @Test
    fun fetchCoinsLoading_displayCoinListLoading() {
        coinFlow = flow {
            emit(Resource.Loading())
        }

        coEvery { getCoinsUseCase(any()) } returns coinFlow
        viewModel = CoinListScreenViewModel(getCoinsUseCase)

        composeTestRule.setContent {
            KryptoTheme {
                CoinListScreen(
                    coinItemOnClick = {},
                    viewModel = viewModel
                )
            }
        }

        composeTestRule.onNodeWithTag(TEST_TAG_COIN_LIST_LOADING).assertIsDisplayed()
    }

    @Test
    fun fetchCoinsSuccess_displayCoinListScreenSuccess() {
        coinFlow = flow {
            emit(Resource.Success(listOf()))
        }

        coEvery { getCoinsUseCase(any()) } returns coinFlow
        viewModel = CoinListScreenViewModel(getCoinsUseCase)

        composeTestRule.setContent {
            KryptoTheme {
                CoinListScreen(
                    coinItemOnClick = {},
                    viewModel = viewModel
                )
            }
        }

        composeTestRule.onNodeWithTag(TEST_TAG_COIN_LIST_SCREEN_SUCCESS).assertIsDisplayed()
    }
}
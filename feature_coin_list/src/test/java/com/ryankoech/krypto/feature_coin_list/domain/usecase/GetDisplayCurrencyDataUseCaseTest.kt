package com.ryankoech.krypto.feature_coin_list.domain.usecase

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.ryankoech.krypto.feature_coin_list.data.repository.FakeCoinRepositoryImpl
import com.ryankoech.krypto.feature_coin_list.data.repository.FakeDisplayCurrencies
import com.ryankoech.krypto.feature_coin_list.domain.repository.CoinRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetDisplayCurrencyDataUseCaseTest {

    private val exceptionMessage = "Mock Exception"
    private lateinit var fakeRepository: CoinRepository

    @Before
    fun setUp() {
        fakeRepository = FakeCoinRepositoryImpl()
    }

    @Test
    fun `call flow onStart - return default list`() = runTest {
        val getDisplayCurrencyDataUseCase = GetDisplayCurrencyDataUseCase(fakeRepository)

        getDisplayCurrencyDataUseCase().test {

            assertThat(awaitItem().data).isEqualTo(getDisplayCurrencyDataUseCase.defaultList)
            awaitItem()
            awaitComplete()

        }
    }

    @Test
    fun `repository throw exception - return Result type Error with default list`() = runTest {

        val mockRepository = mockk<CoinRepository>()

        coEvery { mockRepository.getDisplayCurrencyData() } throws Exception(exceptionMessage)

        val getDisplayCurrencyDataUseCase = GetDisplayCurrencyDataUseCase(mockRepository)

        getDisplayCurrencyDataUseCase().test {

            awaitItem()
            val result = awaitItem()
            assertThat(result.data).isEqualTo(getDisplayCurrencyDataUseCase.defaultList)
            assertThat(result.message).isEqualTo(exceptionMessage)
            awaitComplete()

        }
    }

    @Test
    fun `repository return DisplayCurrency List, return Result type Success`() = runTest {

        val getDisplayCurrencyDataUseCase = GetDisplayCurrencyDataUseCase(fakeRepository)

        getDisplayCurrencyDataUseCase().test {
            awaitItem()
            assertThat(awaitItem().data).isEqualTo(FakeDisplayCurrencies)
            awaitComplete()
        }

    }

}
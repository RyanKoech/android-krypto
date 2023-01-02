package com.ryankoech.krypto.feature_home.domain.usecase

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.ryankoech.krypto.feature_home.data.repository.FakeDisplayCurrencies
import com.ryankoech.krypto.feature_home.data.repository.FakeOwnedCoinsRepositoryImpl
import com.ryankoech.krypto.feature_home.domain.repository.OwnedCoinsRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest

import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetDisplayCurrencyDataUseCaseTest {

    private val exceptionMessage = "Mock Exception"
    private lateinit var fakeRepository: OwnedCoinsRepository

    @Before
    fun setUp() {
        fakeRepository = FakeOwnedCoinsRepositoryImpl()
    }

    @Test
    fun `call flow onStart - return default list`() = runTest{
        val getDisplayCurrencyDataUseCase = GetDisplayCurrencyDataUseCase(fakeRepository)

        getDisplayCurrencyDataUseCase().test {

            assertThat(awaitItem().data).isEqualTo(getDisplayCurrencyDataUseCase.defaultList)
            awaitItem()
            awaitComplete()

        }
    }

    @Test
    fun `repository throw exception - return Result type Error` () = runTest {

        val mockRepository = mockk<OwnedCoinsRepository>()

        coEvery { mockRepository.getDisplayCurrencyData() } throws Exception(exceptionMessage)

        val getDisplayCurrencyDataUseCase = GetDisplayCurrencyDataUseCase(mockRepository)

        getDisplayCurrencyDataUseCase().test {

            awaitItem()
            assertThat(awaitItem().message).isEqualTo(exceptionMessage)
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
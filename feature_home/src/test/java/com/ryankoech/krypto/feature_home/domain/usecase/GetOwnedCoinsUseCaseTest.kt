package com.ryankoech.krypto.feature_home.domain.usecase

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.ryankoech.krypto.common.core.util.Resource
import com.ryankoech.krypto.feature_home.data.repository.FakeOwnedCoins
import com.ryankoech.krypto.feature_home.data.repository.FakeOwnedCoinsRepositoryImpl
import com.ryankoech.krypto.feature_home.domain.repository.OwnedCoinsRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest

import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetOwnedCoinsUseCaseTest {

    private lateinit var fakeRepository: OwnedCoinsRepository
    private val exceptionMessage = "Mock Exception"

    @Before
    fun setUp() {
        fakeRepository = FakeOwnedCoinsRepositoryImpl()
    }

    @Test
    fun `call flow onStart, return Result type Loading`() = runTest {
        val getOwnedCoinsUseCase = GetOwnedCoinsUseCase(fakeRepository)

        getOwnedCoinsUseCase().test {
            assertThat(awaitItem()).isInstanceOf(Resource.Loading::class.java)
            awaitItem()
            awaitComplete()
        }
    }

    @Test
    fun `repository return owned coins list, return Result type Success`() = runTest {
        val getOwnedCoinsUseCase = GetOwnedCoinsUseCase(fakeRepository)

        getOwnedCoinsUseCase().test {
            awaitItem()
            assertThat(awaitItem().data).isEqualTo(FakeOwnedCoins)
            awaitComplete()
        }
    }

    @Test
    fun `repository throw exception, return Result type Error`() = runTest {
        val mockRepository = mockk<OwnedCoinsRepository>()
        coEvery { mockRepository.getOwnedCoins() } throws Exception(exceptionMessage)
        val getOwnedCoinsUseCase = GetOwnedCoinsUseCase(mockRepository)

        getOwnedCoinsUseCase().test {
            awaitItem()
            assertThat(awaitItem().message).isEqualTo(exceptionMessage)
            awaitComplete()
        }
    }


}
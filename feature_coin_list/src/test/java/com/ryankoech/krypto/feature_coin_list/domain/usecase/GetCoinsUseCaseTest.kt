package com.ryankoech.krypto.feature_coin_list.domain.usecase

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.ryankoech.krypto.common.core.util.Resource
import com.ryankoech.krypto.feature_coin_list.data.dto.toCoinEntity
import com.ryankoech.krypto.feature_coin_list.data.repository.FAKE_COIN_LIST
import com.ryankoech.krypto.feature_coin_list.data.repository.FakeCoinRepositoryImpl
import com.ryankoech.krypto.feature_coin_list.presentation.viewmodel.CoinListScreenViewModel.Companion.DEFAULT_SORT_INFO
import com.ryankoech.krypto.feature_coin_list.domain.repository.CoinRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest

import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class GetCoinsUseCaseTest {

    private lateinit var repository: CoinRepository
    private lateinit var getCoinsUseCase: GetCoinsUseCase

    @Test
    fun `response successful, return Resource type Success with data`() = runTest {
        getCoinsUseCase = GetCoinsUseCase(FakeCoinRepositoryImpl())

        getCoinsUseCase(DEFAULT_SORT_INFO).test {

            awaitItem()
            val resource = awaitItem()
            assertThat(resource).isInstanceOf(Resource.Success::class.java)
            assertThat(resource.data).isEqualTo(FAKE_COIN_LIST.toCoinEntity())
            awaitComplete()

        }
    }

    @Test
    fun `call flow onStart, return Resource type Loading`() = runTest{
        getCoinsUseCase = GetCoinsUseCase(FakeCoinRepositoryImpl())

        getCoinsUseCase(DEFAULT_SORT_INFO).test {

            assertThat(awaitItem()).isInstanceOf(Resource.Loading::class.java)
            awaitItem()
            awaitComplete()

        }
    }

    @Test
    fun `repository return null data, return Resource type Error`() = runTest {
        repository = mockk()
        coEvery { repository.getCoins() } returns Response.success(null)
        getCoinsUseCase = GetCoinsUseCase(repository)

        getCoinsUseCase(DEFAULT_SORT_INFO).test {

            awaitItem()
            assertThat(awaitItem()).isInstanceOf(Resource.Error::class.java)
            awaitComplete()

        }
    }

    @Test
    fun `repository return empty list, return Resource type Error`() = runTest {
        repository = mockk()
        coEvery { repository.getCoins() } returns Response.success(listOf())
        getCoinsUseCase = GetCoinsUseCase(repository)

        getCoinsUseCase(DEFAULT_SORT_INFO).test {

            awaitItem()
            assertThat(awaitItem()).isInstanceOf(Resource.Error::class.java)
            awaitComplete()

        }
    }

    @Test
    fun `repository throws exception, return Resource type Error`() = runTest {
        val exceptionMessage = "Mock Exception Message"
        repository = mockk()
        coEvery { repository.getCoins() } throws Exception(exceptionMessage)
        getCoinsUseCase = GetCoinsUseCase(repository)

        getCoinsUseCase(DEFAULT_SORT_INFO).test {

            awaitItem()
            val resource = awaitItem()
            assertThat(resource).isInstanceOf(Resource.Error::class.java)
            assertThat(resource.message).isEqualTo(exceptionMessage)
            awaitComplete()

        }
    }

}
package com.ryankoech.krypto.feature_coin_list.domain.usecase

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.ryankoech.krypto.common.core.util.Resource
import com.ryankoech.krypto.feature_coin_list.data.dto.toCoinEntity
import com.ryankoech.krypto.feature_coin_list.data.dto.toLocalCoinDto
import com.ryankoech.krypto.feature_coin_list.data.repository.FAKE_COIN_LIST
import com.ryankoech.krypto.feature_coin_list.data.repository.FakeCoinRepositoryImpl
import com.ryankoech.krypto.feature_coin_list.presentation.viewmodel.CoinListScreenViewModel.Companion.DEFAULT_SORT_INFO
import com.ryankoech.krypto.feature_coin_list.domain.repository.CoinRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody

import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class GetCoinsUseCaseTest {

    private lateinit var repository: CoinRepository
    private lateinit var getCoinsUseCase: GetCoinsUseCase
    private val exceptionMessage = "Mock Exception Message"

    @Test
    fun `remote response successful, return Resource type Success with data`() = runTest {
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
    fun `remote response unsuccessful local successful, return Resource type Success with data`() = runTest {
        repository = mockk()
        val responseBody = mockk<ResponseBody>(
            relaxed = true
        )
        coEvery { repository.getCoins() } returns Response.error(400, responseBody)
        coEvery { repository.getLocalCoins() } returns FAKE_COIN_LIST.toLocalCoinDto()
        getCoinsUseCase = GetCoinsUseCase(repository)

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
    fun `remote return null data local return list, return Resource type Success  with data`() = runTest {
        repository = mockk()
        coEvery { repository.getCoins() } returns Response.success(null)
        coEvery { repository.getLocalCoins() } returns FAKE_COIN_LIST.toLocalCoinDto()
        getCoinsUseCase = GetCoinsUseCase(repository)

        getCoinsUseCase(DEFAULT_SORT_INFO).test {

            awaitItem()
            val resource = awaitItem()
            assertThat(resource).isInstanceOf(Resource.Success::class.java)
            assertThat(resource.data).isEqualTo(FAKE_COIN_LIST.toCoinEntity())
            awaitComplete()

        }
    }

    @Test
    fun `remote return empty list local return list, return Resource type Success with data`() = runTest {
        repository = mockk()
        coEvery { repository.getCoins() } returns Response.success(listOf())
        coEvery { repository.getLocalCoins() } returns FAKE_COIN_LIST.toLocalCoinDto()
        getCoinsUseCase = GetCoinsUseCase(repository)

        getCoinsUseCase(DEFAULT_SORT_INFO).test {

            awaitItem()
            val resource = awaitItem()
            assertThat(resource).isInstanceOf(Resource.Success::class.java)
            assertThat(resource.data).isEqualTo(FAKE_COIN_LIST.toCoinEntity())
            awaitComplete()

        }
    }

    @Test
    fun `remote throws exception list local return list, return Resource type Success with data`() = runTest {
        repository = mockk()
        coEvery { repository.getCoins() } throws Exception(exceptionMessage)
        coEvery { repository.getLocalCoins() } returns FAKE_COIN_LIST.toLocalCoinDto()
        getCoinsUseCase = GetCoinsUseCase(repository)

        getCoinsUseCase(DEFAULT_SORT_INFO).test {

            awaitItem()
            val resource = awaitItem()
            assertThat(resource).isInstanceOf(Resource.Success::class.java)
            assertThat(resource.data).isEqualTo(FAKE_COIN_LIST.toCoinEntity())
            awaitComplete()

        }
    }

    @Test
    fun `remote unsuccessful local throw Exception, return Resource type Error`() = runTest {
        repository = mockk()
        val responseBody = mockk<ResponseBody>(
            relaxed = true
        )
        coEvery { repository.getCoins() } returns Response.error(400, responseBody)
        coEvery { repository.getLocalCoins() } throws Exception(exceptionMessage)
        getCoinsUseCase = GetCoinsUseCase(repository)

        getCoinsUseCase(DEFAULT_SORT_INFO).test {

            awaitItem()
            val resource = awaitItem()
            assertThat(resource).isInstanceOf(Resource.Error::class.java)
            assertThat(resource.message).isEqualTo(exceptionMessage)
            awaitComplete()

        }
    }

    @Test
    fun `remote unsuccessful local return empty list, return Resource type Error`() = runTest {
        repository = mockk()
        coEvery { repository.getCoins() } throws Exception(exceptionMessage)
        coEvery { repository.getLocalCoins() } returns listOf()
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
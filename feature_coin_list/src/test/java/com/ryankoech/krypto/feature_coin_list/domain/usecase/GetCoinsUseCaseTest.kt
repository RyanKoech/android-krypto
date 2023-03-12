package com.ryankoech.krypto.feature_coin_list.domain.usecase

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.ryankoech.krypto.common.core.util.Resource
import com.ryankoech.krypto.feature_coin_list.data.dto.toCoinEntity
import com.ryankoech.krypto.feature_coin_list.data.dto.toLocalCoinDto
import com.ryankoech.krypto.feature_coin_list.data.repository.FAKE_COIN_LIST
import com.ryankoech.krypto.feature_coin_list.data.repository.FakeCoinRepositoryImpl
import com.ryankoech.krypto.feature_coin_list.domain.entity.Coin
import com.ryankoech.krypto.feature_coin_list.domain.entity.Order
import com.ryankoech.krypto.feature_coin_list.domain.entity.SortCoinBy
import com.ryankoech.krypto.feature_coin_list.domain.entity.SortInfo
import com.ryankoech.krypto.feature_coin_list.presentation.viewmodel.CoinListScreenViewModel.Companion.DEFAULT_SORT_INFO
import com.ryankoech.krypto.feature_coin_list.domain.repository.CoinRepository
import com.ryankoech.krypto.feature_coin_list.domain.usecase.GetCoinsUseCase.Companion.sortData
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import org.junit.Before

import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class GetCoinsUseCaseTest {

    private val sortByPrice = "price"
    private val sortByTotalVolume = "totalVolume"
    private val sortByMarketCap = "marketCap"
    private val rank1 = "#1"
    private val rank3 = "#3"
    private val exceptionMessage = "Mock Exception Message"
    private lateinit var repository: CoinRepository
    private lateinit var getCoinsUseCase: GetCoinsUseCase
    private lateinit var testCoinsList : List<Coin>

    @Before
    fun setUp() {
        testCoinsList = listOf(
            Coin(
                id = "$sortByPrice$rank1,$sortByMarketCap$rank3",
                name = "coin1",
                marketCap = 0,
                marketCapRank = 3,
                symbol = "",
                image = "",
                change = 0f,
                price = 3.0,
                allTimeHigh = 0.0,
                high24Hr = 0.0,
                totalVolume = 2.0,
            ),
            Coin(
                id = "$sortByTotalVolume$rank1",
                name = "coin2",
                marketCap = 0,
                marketCapRank = 2,
                symbol = "",
                image = "",
                change = 0f,
                price = 2.0,
                allTimeHigh = 0.0,
                high24Hr = 0.0,
                totalVolume = 3.0,
            ),
            Coin(
                id = "$sortByPrice$rank3,$sortByTotalVolume$rank3,$sortByMarketCap$rank1",
                name = "coin3",
                marketCap = 0,
                marketCapRank = 1,
                symbol = "",
                image = "",
                change = 0f,
                price = 1.0,
                allTimeHigh = 0.0,
                high24Hr = 0.0,
                totalVolume = 1.0,
            )
        )
    }

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

    @Test
    fun `coin list sorting function sort by market cap descending`() {

        testCoinsList.sortData(
            SortInfo(
                sortBy = SortCoinBy.MARKET_CAP,
                order = Order.DESC
            )
        ).apply {
            this.first().id.contains("$sortByMarketCap$rank1")
            this.last().id.contains("$sortByMarketCap$rank3")
        }
    }

    @Test
    fun `coin list sorting function sort by market cap ascending`() {

        testCoinsList.sortData(
            SortInfo(
                sortBy = SortCoinBy.MARKET_CAP,
                order = Order.ASC
            )
        ).apply {
            this.first().id.contains("$sortByMarketCap$rank3")
            this.last().id.contains("$sortByMarketCap$rank1")
        }
    }


    @Test
    fun `coin list sorting function sort by price descending`() {

        testCoinsList.sortData(
            SortInfo(
                sortBy = SortCoinBy.PRICE,
                order = Order.DESC
            )
        ).apply {
            this.first().id.contains("$sortByPrice$rank1")
            this.last().id.contains("$sortByPrice$rank3")
        }
    }


    @Test
    fun `coin list sorting function sort by price ascending`() {

        testCoinsList.sortData(
            SortInfo(
                sortBy = SortCoinBy.PRICE,
                order = Order.ASC
            )
        ).apply {
            this.first().id.contains("$sortByMarketCap$rank3")
            this.last().id.contains("$sortByMarketCap$rank1")
        }
    }

    @Test
    fun `coin list sorting function sort by total volume descending`() {

        testCoinsList.sortData(
            SortInfo(
                sortBy = SortCoinBy.TOTAL_VOLUME,
                order = Order.DESC
            )
        ).apply {
            this.first().id.contains("$sortByTotalVolume$rank1")
            this.last().id.contains("$sortByPrice$rank3")
        }
    }


    @Test
    fun `coin list sorting function sort by total volume ascending`() {

        testCoinsList.sortData(
            SortInfo(
                sortBy = SortCoinBy.TOTAL_VOLUME,
                order = Order.ASC
            )
        ).apply {
            this.first().id.contains("$sortByTotalVolume$rank3")
            this.last().id.contains("$sortByPrice$rank1")
        }
    }

}
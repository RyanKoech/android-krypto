package com.ryankoech.krypto.feature_home.domain.usecase

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.ryankoech.krypto.common.core.util.Resource
import com.ryankoech.krypto.feature_home.core.util.EXCEPTION_MESSAGE
import com.ryankoech.krypto.feature_home.data.dto.owned_coin.OwnedCoinDto
import com.ryankoech.krypto.feature_home.data.repository.FakeOwnedCoinsRepositoryImpl
import com.ryankoech.krypto.feature_home.domain.repository.OwnedCoinsRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class SaveOwnedCoinUseCaseTest {

    private lateinit var saveOwnedCoinsUseCase: SaveOwnedCoinUseCase
    private lateinit var repository: OwnedCoinsRepository
    private lateinit var ownedCoinDto : OwnedCoinDto

    @Before
    fun setUp(){
        ownedCoinDto = OwnedCoinDto(
            id = "bitcoin",
            symbol = "btc",
            amount = 1.0,
            value = 17434.25,
            change = -3.60164f,
            icon = "https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1547033579"
        )
    }

    @Test
    fun `repository saved display currency data, return Resource type Success`() = runTest {
        repository = FakeOwnedCoinsRepositoryImpl()
        saveOwnedCoinsUseCase = SaveOwnedCoinUseCase(repository)

        saveOwnedCoinsUseCase(ownedCoinDto).test {
            val resource = awaitItem()
            assertThat(resource).isInstanceOf(Resource.Success::class.java)
            assertThat(resource.data).isEqualTo(ownedCoinDto.id)
            awaitComplete()
        }
    }

    @Test
    fun `repository throws exception, return Resource type Error`() = runTest {
        repository = mockk()
        coEvery { repository.saveOwnedCoin(any()) } throws Exception(EXCEPTION_MESSAGE)

        saveOwnedCoinsUseCase = SaveOwnedCoinUseCase(repository)

        saveOwnedCoinsUseCase(ownedCoinDto).test {
            val resource = awaitItem()
            assertThat(resource).isInstanceOf(Resource.Error::class.java)
            assertThat(resource.message).isEqualTo(EXCEPTION_MESSAGE)
            awaitComplete()
        }

    }

}
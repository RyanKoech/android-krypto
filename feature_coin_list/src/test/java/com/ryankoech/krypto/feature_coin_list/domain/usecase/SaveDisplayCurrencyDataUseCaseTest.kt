package com.ryankoech.krypto.feature_coin_list.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.ryankoech.krypto.common.core.util.Resource
import com.ryankoech.krypto.common.presentation.util.DisplayCurrency
import com.ryankoech.krypto.feature_coin_list.data.repository.FakeCoinRepositoryImpl
import com.ryankoech.krypto.feature_coin_list.domain.repository.CoinRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class SaveDisplayCurrencyDataUseCaseTest {

    private lateinit var saveDisplayCurrencyDataUseCase: SaveDisplayCurrencyDataUseCase
    private lateinit var repository: CoinRepository

    @Test
    fun `repository saved display currency data, return Resource type Success`() = runTest {
        repository = FakeCoinRepositoryImpl()
        saveDisplayCurrencyDataUseCase = SaveDisplayCurrencyDataUseCase(repository)
        val hashMap = hashMapOf<DisplayCurrency, Double>()

        val resource = saveDisplayCurrencyDataUseCase(hashMap)

        assertThat(resource).isInstanceOf(Resource.Success::class.java)
    }

    @Test
    fun `repository throws exception, return Resource type Error`() = runTest {
        repository = mockk()
        coEvery { repository.saveDisplayCurrencyData(any()) } throws Exception(EXCEPTION_MESSAGE)

        saveDisplayCurrencyDataUseCase = SaveDisplayCurrencyDataUseCase(repository)
        val hashMap = hashMapOf<DisplayCurrency, Double>()

        val resource = saveDisplayCurrencyDataUseCase(hashMap)

        assertThat(resource.message).isEqualTo(EXCEPTION_MESSAGE)
    }
}

private  const val EXCEPTION_MESSAGE = "Test Exception Message"

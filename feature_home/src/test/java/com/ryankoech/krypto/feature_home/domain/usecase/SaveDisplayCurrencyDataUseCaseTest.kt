package com.ryankoech.krypto.feature_home.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.ryankoech.krypto.common.core.util.Resource
import com.ryankoech.krypto.common.presentation.util.DisplayCurrency
import com.ryankoech.krypto.feature_home.core.util.EXCEPTION_MESSAGE
import com.ryankoech.krypto.feature_home.data.repository.FakeOwnedCoinsRepositoryImpl
import com.ryankoech.krypto.feature_home.domain.repository.OwnedCoinsRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class SaveDisplayCurrencyDataUseCaseTest {

    private lateinit var saveDisplayCurrencyDataUseCase: SaveDisplayCurrencyDataUseCase
    private lateinit var repository: OwnedCoinsRepository

    @Test
    fun `repository saved display currency data, return Resource type Success`() = runTest {
        repository = FakeOwnedCoinsRepositoryImpl()
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
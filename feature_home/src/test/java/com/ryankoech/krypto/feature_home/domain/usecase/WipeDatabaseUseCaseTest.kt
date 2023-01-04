package com.ryankoech.krypto.feature_home.domain.usecase

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.ryankoech.krypto.common.core.util.Resource
import com.ryankoech.krypto.feature_home.core.util.EXCEPTION_MESSAGE
import com.ryankoech.krypto.feature_home.data.repository.FakeOwnedCoinsRepositoryImpl
import com.ryankoech.krypto.feature_home.domain.repository.OwnedCoinsRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest

import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class WipeDatabaseUseCaseTest {

    private lateinit var wipeDatabaseUseCase: WipeDatabaseUseCase
    private lateinit var repository: OwnedCoinsRepository

    @Before
    fun setUp() {
    }

    @Test
    fun `repository throw Exception, return Resource type Error`() = runTest {
        repository = mockk()
        coEvery { repository.wipeDatabase() } throws Exception(EXCEPTION_MESSAGE)
        wipeDatabaseUseCase = WipeDatabaseUseCase(repository)

        wipeDatabaseUseCase().test {
            assertThat(awaitItem()).isInstanceOf(Resource.Error::class.java)
            awaitComplete()
        }
    }

    @Test
    fun `repository wipes database, return Resource type Success`() = runTest {
        repository = FakeOwnedCoinsRepositoryImpl()
        wipeDatabaseUseCase = WipeDatabaseUseCase(repository)

        wipeDatabaseUseCase().test {
            assertThat(awaitItem()).isInstanceOf(Resource.Success::class.java)
            awaitComplete()
        }
    }
}
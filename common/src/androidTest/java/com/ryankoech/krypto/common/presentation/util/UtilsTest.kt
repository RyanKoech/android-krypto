package com.ryankoech.krypto.common.presentation.util

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

class UtilsTest {

    private lateinit var appContext : Context

    @Before
    fun setUp() {
        appContext = InstrumentationRegistry.getInstrumentation().targetContext
    }

    @After
    fun tearDown() {
    }



    @Test
    fun getFormattedBalance_OneDecimalPlace_returnOneDecimalPlace () {
        val balance = 100.1
        val expectedBalanceString = "100.1"

        val result = getInTwoDecimalPlaces(balance)

        assertThat(result).isEqualTo(expectedBalanceString)
    }

    @Test
    fun getFormattedBalance_TwoDecimalPlace_returnTwoDecimalPlace () {
        val balance = 100.12
        val expectedBalanceString = "100.12"

        val result = getInTwoDecimalPlaces(balance)

        assertThat(result).isEqualTo(expectedBalanceString)
    }

    @Test
    fun getInTwoDecimalPlaces_MoreThanTwoDecimalPlace_returnTwoDecimalPlace () {
        val balance = 100.127
        val expectedBalanceString = "100.13"

        val result = getInTwoDecimalPlaces(balance)

        assertThat(result).isEqualTo(expectedBalanceString)
    }

    @Test
    fun getFormattedBalance_GivenUsd_returnWithOutSpaceFromSymbol () {
        val balance = 100.00
        val expectedBalanceString = "$100"

        val result = getFormattedBalance(appContext, balance, DisplayCurrency.USD)

        assertThat(result).isEqualTo(expectedBalanceString)
    }

    @Test
    fun getFormattedBalance_GivenCryptoCurrency_returnWithSpaceFromSymbol () {
        val balance = 100.00
        val cryptoCurrencies = listOf(
            DisplayCurrency.LTC,
            DisplayCurrency.BTC,
            DisplayCurrency.BNB,
            DisplayCurrency.ETH
        )

        cryptoCurrencies.forEach{ cryptoCurrency ->

            val expectedBalanceString = "$cryptoCurrency 100"

            val result = getFormattedBalance(appContext, balance, cryptoCurrency)

            assertThat(result).isEqualTo(expectedBalanceString)
        }
    }

    @Test
    fun getFormattedChange_GivenZero_return0() {
        val change = 0F
        val expectedChangeString = "0%"

        val result = getFormattedChange(appContext, change)

        assertThat(result).isEqualTo(expectedChangeString)
    }


    @Test
    fun getFormattedChange_GivenLessThanZero_return0() {
        val change = -2.101F
        val expectedChangeString = "-2.1%"

        val result = getFormattedChange(appContext, change)

        assertThat(result).isEqualTo(expectedChangeString)
    }


    @Test
    fun getFormattedChange_GivenMoreThanZero_return0() {
        val change = 3.004F
        val expectedChangeString = "+3%"

        val result = getFormattedChange(appContext, change)

        assertThat(result).isEqualTo(expectedChangeString)
    }

}
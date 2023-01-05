package com.ryankoech.krypto.feature_home.data.data_source.local.shared_pref

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.ryankoech.krypto.common.presentation.util.DisplayCurrency
import com.ryankoech.krypto.feature_home.core.ktx.toDisplayCurrencyList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class HomeLocalPrefTest {

    private var homeLocalPref: HomeLocalPref? = null

    @Before
    fun setUp() {
        homeLocalPref = HomeLocalPref(ApplicationProvider.getApplicationContext<Context?>().applicationContext)
    }

    @After
    fun tearDown() {
        homeLocalPref = null
    }

    @Test
    fun insert_display_currency_map__return_sort_display_currency_list() = runTest{
        val fakeDisplayCurrencyMap : HashMap<DisplayCurrency, Double> = hashMapOf(
            DisplayCurrency.LTC to 1.0,
            DisplayCurrency.USD to 2.0,
            DisplayCurrency.ETH to 3.0,
            DisplayCurrency.BTC to 4.0,
            DisplayCurrency.BNB to 5.0,
        )
        homeLocalPref!!.saveDisplayCurrencyData(fakeDisplayCurrencyMap)

        runBlocking {
            val retrievedDisplayCurrencyList = homeLocalPref!!.getDisplayCurrencyData()
            assertThat(retrievedDisplayCurrencyList).isEqualTo(fakeDisplayCurrencyMap.toDisplayCurrencyList())
        }
    }

}
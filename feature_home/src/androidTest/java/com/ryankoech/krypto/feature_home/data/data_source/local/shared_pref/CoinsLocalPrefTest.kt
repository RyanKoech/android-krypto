package com.ryankoech.krypto.feature_home.data.data_source.local.shared_pref

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.ryankoech.krypto.common.presentation.util.DisplayCurrency
import com.ryankoech.krypto.feature_coin_list.data.data_source.local.sharedl_pref.CoinsLocalPref
import com.ryankoech.krypto.feature_coin_list.core.ktx.toDisplayCurrencyList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class CoinsLocalPrefTest {

    private var coinsLocalPref: CoinsLocalPref? = null

    @Before
    fun setUp() {
        coinsLocalPref = CoinsLocalPref(ApplicationProvider.getApplicationContext<Context?>().applicationContext)
    }

    @After
    fun tearDown() {
        coinsLocalPref = null
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
        coinsLocalPref!!.saveDisplayCurrencyData(fakeDisplayCurrencyMap)

        runBlocking {
            val retrievedDisplayCurrencyList = coinsLocalPref!!.getDisplayCurrencyData()
            assertThat(retrievedDisplayCurrencyList).isEqualTo(fakeDisplayCurrencyMap.toDisplayCurrencyList())
        }
    }

}
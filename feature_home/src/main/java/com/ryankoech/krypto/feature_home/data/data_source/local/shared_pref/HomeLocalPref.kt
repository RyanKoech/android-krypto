package com.ryankoech.krypto.feature_home.data.data_source.local.shared_pref

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ryankoech.krypto.common.presentation.util.DisplayCurrency
import com.ryankoech.krypto.feature_home.core.ktx.toDisplayCurrencyList
import com.ryankoech.krypto.feature_home.data.dto.display_currency.DisplayCurrencyDto
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.coroutineScope
import timber.log.Timber
import javax.inject.Inject

class HomeLocalPref @Inject constructor(
    @ApplicationContext private val context : Context
) {

    companion object {
        private const val PREF_NAME = "feature_home_local_pref"
        private const val DISPLAY_CURRENCY_KEY = "display_currency_data"
    }

    private var prefs: SharedPreferences? = null
    private val gson by lazy { Gson() }

    suspend fun saveDisplayCurrencyData(displayCurrencyDataMap : HashMap<DisplayCurrency, Double>) {
        val displayCurrencyData = displayCurrencyDataMap.toDisplayCurrencyList()
        return coroutineScope {
            try {
                if (prefs == null)
                    prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
                val editor = prefs?.edit()
                editor?.putString(DISPLAY_CURRENCY_KEY, gson.toJson(displayCurrencyData))
                editor?.apply()
            } catch (e :Exception){
                Timber.e(e)
            }
        }
    }

    suspend fun getDisplayCurrencyData() : List<DisplayCurrencyDto>? {
        return coroutineScope {
            try{
                if (prefs == null)
                    prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

                val json = prefs?.getString(DISPLAY_CURRENCY_KEY, "")
                val displayCurrencyData: List<DisplayCurrencyDto> =
                    gson.fromJson(json, object : TypeToken<List<DisplayCurrencyDto>>() {}.type)
                displayCurrencyData
            }catch (e : Exception) {
                Timber.e(e)
                null
            }
        }
    }

}
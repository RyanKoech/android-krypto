package com.ryankoech.krypto.feature_home.data.data_source.local.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.ryankoech.krypto.feature_home.data.dto.owned_coin.OwnedCoinDto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class OwnedCoinsDaoTest {

    private lateinit var database: OwnedCoinsDatabase
    private lateinit var dao : OwnedCoinsDao

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext<Context?>().applicationContext,
            OwnedCoinsDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
        dao = database.ownedCoinDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insert_into_and_fetch_from_Db__contain_element_in_list() = runTest{
        val ownedCoin = OwnedCoinDto(
            id = "bitcoin",
            symbol = "btc",
            amount = 1.0,
            value = 17434.25,
            change = -3.60164f,
            icon = "https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1547033579"
        )

        dao.insertNewCoin(ownedCoin)

        runBlocking{
            val listOfOwnedCoins = dao.getOwnedCoins()
            assertThat(listOfOwnedCoins).contains(ownedCoin)
        }
    }

}
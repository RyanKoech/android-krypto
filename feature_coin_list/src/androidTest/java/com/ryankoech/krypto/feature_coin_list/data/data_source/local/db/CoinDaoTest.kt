package com.ryankoech.krypto.feature_coin_list.data.data_source.local.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.ryankoech.krypto.feature_coin_list.data.dto.toLocalCoinDto
import com.ryankoech.krypto.feature_coin_list.data.repository.FAKE_COIN_LIST
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class CoinDaoTest {


    private lateinit var database: CoinDatabase
    private lateinit var dao : CoinDao

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext<Context?>().applicationContext,
            CoinDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
        dao = database.getCoinDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insert_into_and_fetch_list_from_Db__both_lists_equal() = runTest{

        dao.insertCoins(FAKE_COIN_LIST.toLocalCoinDto())

        runBlocking{
            val listOfCoins = dao.getCoins()
            assertThat(listOfCoins).isEqualTo(FAKE_COIN_LIST.toLocalCoinDto())
        }
    }


    @Test
    fun insert_into_and_fetch_from_Db__contain_element_in_list() = runTest{

        dao.insertCoins(FAKE_COIN_LIST.toLocalCoinDto())

        runBlocking{
            val coin = dao.getCoin(FAKE_COIN_LIST.toLocalCoinDto().first().id)
            assertThat(coin).isEqualTo(FAKE_COIN_LIST.toLocalCoinDto().first())
        }
    }


}
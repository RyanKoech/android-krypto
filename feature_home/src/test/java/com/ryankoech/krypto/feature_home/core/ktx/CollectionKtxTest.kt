package com.ryankoech.krypto.feature_home.core.ktx

import com.google.common.truth.Truth.assertThat
import org.junit.Assert
import org.junit.Before
import org.junit.Test

internal class CollectionKtxTest {

    private lateinit var testList : List<Int>

    @Before
    fun setUp() {
        testList = listOf(0,1,2)
    }

    @Test
    fun `getNextIndex with index less than 0 throw exception`() {
        try {
            testList.getNextIndex(-1)
            Assert.fail("Exception not thrown with index less than 0")
        }catch (e : Throwable) {
            assertThat(e.message).isEqualTo("Provided Current Index is out of bounds")
        }
    }

    @Test
    fun `getNextIndex with index greater than or equal to size throw exception`() {
        try {
            testList.getNextIndex(testList.size)
            Assert.fail("Exception not thrown with index greater than or equal to size")
        }catch (e : Throwable) {
            assertThat(e.message).isEqualTo("Provided Current Index is out of bounds")
        }
    }

    @Test
    fun `getNextIndex with correct index return next index`() {
        val correctIndex = 0
        val nextIndex = testList.getNextIndex(correctIndex)
        assertThat(nextIndex).isEqualTo(correctIndex + 1)
    }

    @Test
    fun `getNextIndex with last index return first index`() {
        val nextIndex = testList.getNextIndex(testList.size-1)
        assertThat(nextIndex).isEqualTo(0)
    }
}
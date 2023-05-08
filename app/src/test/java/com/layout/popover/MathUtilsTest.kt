package com.layout.popover

import com.layout.popover.unittest.MathUtils
import org.junit.Assert.*

import org.junit.Test

class MathUtilsTest {
    @Test
    fun factorialValuePassedFactorialReturned() {
        val mathUtils = MathUtils()
        val result = mathUtils.factorial(5)
        assertEquals(120, result)
    }

    @Test
    fun factorial() {
        val mathUtils = MathUtils()
        val result = mathUtils.factorial(5)
        assertTrue(result == 120)
    }

    @Test
    fun factorialZeroValuePassedReturnedOne() {
        val mathUtils = MathUtils()
        val result = mathUtils.factorial(0)
        assertEquals(1, result)
    }
}
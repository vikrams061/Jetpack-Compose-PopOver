package com.layout.popover

import com.layout.popover.unittest.ArrayUtils
import org.junit.Assert.*

import org.junit.Test

class ArrayUtilsTest {

    @Test
    fun findLargestNumber() {
        val arrayUtils = ArrayUtils()
        val numbers = intArrayOf(2, 4, 1, 5, 3)
        val result = arrayUtils.findLargestNumber(numbers)
        assertEquals(5, result)
    }
}
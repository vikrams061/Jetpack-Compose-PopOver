package com.layout.popover

import com.layout.popover.unittest.Calculator
import org.junit.Assert.*

import org.junit.Test

class CalculatorTest {

    @Test
    fun addTwoNumbersAdditionReturned() {
        val calculator = Calculator()
        val result = calculator.add(2, 3)
        assertEquals(5, result)
    }

    @Test
    fun addPassedNegativeNumbersAdditionReturned() {
        val calculator = Calculator()
        val result = calculator.add(-2, -3)
        assertEquals(-1, result)
    }
}
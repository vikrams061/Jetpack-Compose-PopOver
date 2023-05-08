package com.layout.popover

import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class MainClassTest {
    val positiveNumber = MainClass()
    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun isPositiveNumber() {
        val result = positiveNumber.isPositiveNumber(2)
        assertEquals(true,result)

    }

    @Test
    fun addNumber(){
        val result = positiveNumber.addNumber(2,3)
        assertEquals(5,result)
    }
}
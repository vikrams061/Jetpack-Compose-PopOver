package com.layout.popover

import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.AllOf.allOf
import org.hamcrest.core.AnyOf.anyOf
import org.hamcrest.core.IsEqual.equalTo
import org.hamcrest.core.IsInstanceOf.instanceOf
import org.hamcrest.core.IsNot.not
import org.junit.After
import org.junit.Before

import org.junit.Test

class HemCrestTest {

    @Before
    fun setUp() {
        // set up any data or objects needed for the test case
    }

    @After
    fun tearDown() {
        // clean up any data or objects created during the test case
    }

    @Test
    fun useEqualTo() {
        val expected = "Augusta"
        val actual = "Augusta"
        assertThat(actual, equalTo(expected))
    }

    @Test
    fun useInstanceOf() {
        val obj: Any = "Hello"
        assertThat(obj, instanceOf(String::class.java))
    }

    @Test
    fun testNot() {
        val actualString = "Hello, world!"
        val expectedString = "Goodbye, world!"
        assertThat(actualString, not(equalTo(expectedString)))
    }

    @Test
    fun testAnyOf() {
        val actualString = "Hello, world!"
        assertThat(actualString, anyOf(
            equalTo("Goodbye, world!"),
            containsString("world")
        ))
    }

    @Test
    fun testAllOf() {
        val actualString = "Hello, world!"
        assertThat(actualString, allOf(
            equalTo("Hello, world!"),
            containsString("world")
        ))
    }
}
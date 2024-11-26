package com.github.sikv.habitsplus.util

class MockDateTimeUtils : DateTimeUtils {

    val testTimeMillis = 345L;

    override fun currentTimeMillis(): Long {
        return testTimeMillis
    }
}

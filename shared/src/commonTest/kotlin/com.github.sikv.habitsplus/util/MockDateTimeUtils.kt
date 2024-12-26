package com.github.sikv.habitsplus.util

import com.github.sikv.habitsplus.data.model.Timestamp

class MockDateTimeUtils : DateTimeUtils {

    val testTimeMillis = 345L

    override fun currentTimestamp(): Timestamp {
        return testTimeMillis
    }
}

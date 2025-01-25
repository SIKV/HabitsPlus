package com.github.sikv.habitsplus.util

import com.github.sikv.habitsplus.data.model.Timestamp

class FakeDateTimeUtils : DateTimeUtils {

    val testTimeMillis = 345L

    override fun currentTimestamp(): Timestamp {
        return testTimeMillis
    }

    override fun currentYear(): Int {
        return 0
    }

    override fun getMonthNumber(timestamp: Timestamp): Int {
        return 0
    }
}

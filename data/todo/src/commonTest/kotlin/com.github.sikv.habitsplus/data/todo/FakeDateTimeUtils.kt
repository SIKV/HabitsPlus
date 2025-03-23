package com.github.sikv.habitsplus.data.todo

import com.github.sikv.habitsplus.data.common.DateTimeUtils
import com.github.sikv.habitsplus.data.common.model.Timestamp

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

    override fun getYear(timestamp: Timestamp): Int {
        return 0
    }
}

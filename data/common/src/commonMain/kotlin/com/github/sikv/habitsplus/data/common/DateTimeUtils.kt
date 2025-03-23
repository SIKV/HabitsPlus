package com.github.sikv.habitsplus.data.common

import com.github.sikv.habitsplus.data.common.model.Timestamp

interface DateTimeUtils {
    fun currentTimestamp(): Timestamp
    fun currentYear(): Int
    fun getMonthNumber(timestamp: Timestamp): Int
    fun getYear(timestamp: Timestamp): Int
}

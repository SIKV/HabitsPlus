package com.github.sikv.habitsplus.util

import com.github.sikv.habitsplus.data.model.Timestamp

interface DateTimeUtils {

    fun currentTimestamp(): Timestamp
    fun currentYear(): Int
    fun getMonthNumber(timestamp: Timestamp): Int
    fun getYear(timestamp: Timestamp): Int
}

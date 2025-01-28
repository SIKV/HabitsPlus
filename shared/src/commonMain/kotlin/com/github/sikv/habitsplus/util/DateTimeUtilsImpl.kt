package com.github.sikv.habitsplus.util

import com.github.sikv.habitsplus.data.model.Timestamp
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class DateTimeUtilsImpl : DateTimeUtils {

    override fun currentTimestamp(): Timestamp {
        return Clock.System.now().toEpochMilliseconds()
    }

    override fun currentYear(): Int {
        return Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).year
    }

    override fun getMonthNumber(timestamp: Timestamp): Int {
        val instant = Instant.fromEpochMilliseconds(timestamp)
        return instant.toLocalDateTime(TimeZone.currentSystemDefault()).monthNumber
    }

    override fun getYear(timestamp: Timestamp): Int {
        val instant = Instant.fromEpochMilliseconds(timestamp)
        return instant.toLocalDateTime(TimeZone.currentSystemDefault()).year
    }
}

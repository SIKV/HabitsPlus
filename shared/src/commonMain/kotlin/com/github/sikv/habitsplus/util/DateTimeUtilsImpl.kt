package com.github.sikv.habitsplus.util

import com.github.sikv.habitsplus.data.model.Timestamp
import kotlinx.datetime.Clock

class DateTimeUtilsImpl : DateTimeUtils {

    override fun currentTimestamp(): Timestamp {
        return Clock.System.now().toEpochMilliseconds()
    }
}

package com.github.sikv.habitsplus.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class TodoStatus {

    data object Todo : TodoStatus()

    data class Done(
        @SerialName("doneAtMs")
        val doneAtMs: Long?, // in UTC milliseconds from the epoch.
    ) : TodoStatus()
}

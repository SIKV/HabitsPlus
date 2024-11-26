package com.github.sikv.habitsplus.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Todo(
    @SerialName("id")
    val id: Long = EMPTY_ID,

    @SerialName("status")
    val status: TodoStatus,

    @SerialName("title")
    val title: String,

    @SerialName("description")
    val description: String?,

    @SerialName("dueDateMs")
    val dueDateMs: Long?, // in UTC milliseconds from the epoch.

    @SerialName("addedAtMs")
    val addedAtMs: Long = 0, // in UTC milliseconds from the epoch.

    @SerialName("editedAtMs")
    val editedAtMs: Long? = 0 // in UTC milliseconds from the epoch.
) {

    companion object {
        private const val EMPTY_ID = -1L
    }
}

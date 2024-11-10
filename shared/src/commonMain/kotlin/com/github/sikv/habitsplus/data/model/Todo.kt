package com.github.sikv.habitsplus.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Todo(
    @SerialName("id")
    val id: Long = EMPTY_ID,

    @SerialName("title")
    val title: String,

    @SerialName("description")
    val description: String?,

    @SerialName("dueDateTime")
    val dueDateTime: Long?,

    @SerialName("addedAt")
    val addedAt: Long,

    @SerialName("editedAt")
    val editedAt: Long?
) {

    companion object {
        private const val EMPTY_ID = -1L
    }
}

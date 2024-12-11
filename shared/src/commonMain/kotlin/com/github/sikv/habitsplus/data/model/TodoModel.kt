package com.github.sikv.habitsplus.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TodoModel(
    @SerialName("id")
    val id: Long = AUTOINCREMENT_ID,

    @SerialName("status")
    val status: TodoStatus,

    @SerialName("title")
    val title: String,

    @SerialName("description")
    val description: String?,

    @SerialName("dueDateMs")
    val dueDateMs: Timestamp?,

    @SerialName("addedAtMs")
    val addedAtMs: Timestamp = 0,

    @SerialName("editedAtMs")
    val editedAtMs: Timestamp? = 0
)

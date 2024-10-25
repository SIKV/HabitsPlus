package com.github.sikv.habitsplus.feature.todos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Todo(
    @SerialName("title")
    val title: String
)

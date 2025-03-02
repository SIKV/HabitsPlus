package com.github.sikv.habitsplus.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LabelModel(
    @SerialName("id")
    val id: Long = AUTOINCREMENT_ID,

    @SerialName("title")
    val title: String,

    @SerialName("color")
    val color: String
)

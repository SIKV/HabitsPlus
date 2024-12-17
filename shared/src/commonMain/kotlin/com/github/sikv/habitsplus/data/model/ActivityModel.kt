package com.github.sikv.habitsplus.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ActivityModel(
    @SerialName("id")
    val id: Long = AUTOINCREMENT_ID,

    @SerialName("description")
    val description: String,

    @SerialName("images")
    val images: List<String>,

    @SerialName("date")
    val date: Timestamp,

    @SerialName("metadata")
    val metadata: Metadata = Metadata()
)

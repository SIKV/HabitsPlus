package com.github.sikv.habitsplus.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Metadata(

    @SerialName("addedAt")
    val addedAt: Timestamp = 0,

    @SerialName("editedAt")
    val editedAt: Timestamp? = 0
)

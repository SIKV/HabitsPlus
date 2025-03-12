package com.github.sikv.habitsplus.data.model

data class ActivityModel(
    val id: Long = AUTOINCREMENT_ID,
    val description: String,
    val images: List<String>,
    val date: Timestamp,
    val metadata: Metadata = Metadata()
)

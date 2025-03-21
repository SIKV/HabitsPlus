package com.github.sikv.habitsplus.util

import com.github.sikv.habitsplus.data.model.ActivityModel
import com.github.sikv.habitsplus.data.model.Metadata

val testActivities = listOf(
    ActivityModel(
        id = 1,
        description = "Test 1",
        images = listOf("1"),
        date = 123L,
        metadata = Metadata(
            addedAt = 100L,
            editedAt = null
        )
    ),
    ActivityModel(
        id = 2,
        description = "Test 2",
        images = listOf(),
        date = 1L,
        metadata = Metadata(
            addedAt = 1L,
            editedAt = 2L
        )
    ),
)

val testActivitiesYears = setOf(2023, 2024, 2025)

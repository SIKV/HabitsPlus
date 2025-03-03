package com.github.sikv.habitsplus.data.source

import com.github.sikv.habitsplus.data.model.ActivityModel

internal interface ActivitiesDataSource {
    fun insertActivity(
        description: String,
        images: String?,
        dateMs: Long,
        addedAtMs: Long
    ) : Boolean

    fun updateActivity(
        id: Long,
        description: String,
        images: String?,
        dateMs: Long,
        editedAtMs: Long?
    ) : Boolean

    fun selectActivities(year: Int): List<ActivityModel>

    fun selectActivitiesYears(): Set<Int>
}

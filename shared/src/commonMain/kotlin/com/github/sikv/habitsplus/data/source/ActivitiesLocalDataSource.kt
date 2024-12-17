package com.github.sikv.habitsplus.data.source

import com.github.sikv.habitsplus.data.model.ActivityModel

internal interface ActivitiesLocalDataSource {
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

    fun selectAllActivities(): List<ActivityModel>
}

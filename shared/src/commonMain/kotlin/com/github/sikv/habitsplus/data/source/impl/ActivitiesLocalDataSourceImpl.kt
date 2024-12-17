package com.github.sikv.habitsplus.data.source.impl

import com.github.sikv.habitsplus.data.mapping.mapActivity
import com.github.sikv.habitsplus.data.model.ActivityModel
import com.github.sikv.habitsplus.data.source.ActivitiesLocalDataSource
import com.github.sikv.habitsplus.database.ActivitiesDatabaseManager

class ActivitiesLocalDataSourceImpl(
    private val database: ActivitiesDatabaseManager
) : ActivitiesLocalDataSource {

    override fun insertActivity(
        description: String,
        images: String?,
        dateMs: Long,
        addedAtMs: Long
    ) : Boolean {
        try {
            database.dbQuery.insertActivity(
                description = description,
                images = images,
                date_ms = dateMs,
                added_at_ms = addedAtMs
            )
            return true
        } catch (e: Exception) {
            return false
        }
    }

    override fun updateActivity(
        id: Long,
        description: String,
        images: String?,
        dateMs: Long,
        editedAtMs: Long?
    ): Boolean {
        try {
            database.dbQuery.updateActivity(
                id = id,
                description = description,
                images = images,
                date_ms = dateMs,
                edited_at_ms = editedAtMs
            )
            return true
        } catch (e: Exception) {
            return false
        }
    }

    override fun selectAllActivities(): List<ActivityModel> {
        return database.dbQuery
            .selectAllActivities(::mapActivity)
            .executeAsList()
    }
}

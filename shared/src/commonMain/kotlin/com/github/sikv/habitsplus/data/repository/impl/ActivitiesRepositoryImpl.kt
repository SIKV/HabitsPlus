package com.github.sikv.habitsplus.data.repository.impl

import com.github.sikv.habitsplus.data.mapping.IMAGES_DELIMITER
import com.github.sikv.habitsplus.data.model.ActivityModel
import com.github.sikv.habitsplus.data.repository.ActivitiesRepository
import com.github.sikv.habitsplus.data.source.ActivitiesLocalDataSource
import com.github.sikv.habitsplus.util.DateTimeUtils

internal class ActivitiesRepositoryImpl(
    private val activitiesLocalDataSource: ActivitiesLocalDataSource,
    private val dateTimeUtils: DateTimeUtils
) : ActivitiesRepository {

    override fun addActivity(activity: ActivityModel): Boolean {
        return activitiesLocalDataSource.insertActivity(
            description = activity.description,
            images = activity.images.joinToString(IMAGES_DELIMITER),
            dateMs = activity.date,
            addedAtMs = dateTimeUtils.currentTimestamp()
        )
    }

    override fun updateActivity(activity: ActivityModel): Boolean {
        return activitiesLocalDataSource.updateActivity(
            id = activity.id,
            description = activity.description,
            images = activity.images.joinToString(IMAGES_DELIMITER),
            dateMs = activity.date,
            editedAtMs = dateTimeUtils.currentTimestamp()
        )
    }

    override fun getActivities(year: Int): List<ActivityModel> {
        return activitiesLocalDataSource.selectActivities(year)
    }

    override fun getActivitiesYears(): Set<Int> {
        return activitiesLocalDataSource.selectActivitiesYears()
    }
}

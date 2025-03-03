package com.github.sikv.habitsplus.data.repository.impl

import com.github.sikv.habitsplus.data.mapping.IMAGES_DELIMITER
import com.github.sikv.habitsplus.data.model.ActivityModel
import com.github.sikv.habitsplus.data.repository.ActivitiesRepository
import com.github.sikv.habitsplus.data.source.ActivitiesDataSource
import com.github.sikv.habitsplus.util.DateTimeUtils

internal class ActivitiesRepositoryImpl(
    private val activitiesDataSource: ActivitiesDataSource,
    private val dateTimeUtils: DateTimeUtils
) : ActivitiesRepository {

    override fun addActivity(activity: ActivityModel): Boolean {
        return activitiesDataSource.insertActivity(
            description = activity.description,
            images = activity.images.joinToString(IMAGES_DELIMITER),
            dateMs = activity.date,
            addedAtMs = dateTimeUtils.currentTimestamp()
        )
    }

    override fun updateActivity(activity: ActivityModel): Boolean {
        return activitiesDataSource.updateActivity(
            id = activity.id,
            description = activity.description,
            images = activity.images.joinToString(IMAGES_DELIMITER),
            dateMs = activity.date,
            editedAtMs = dateTimeUtils.currentTimestamp()
        )
    }

    override fun getActivities(year: Int): List<ActivityModel> {
        return activitiesDataSource.selectActivities(year)
    }

    override fun getActivitiesYears(): Set<Int> {
        return activitiesDataSource.selectActivitiesYears()
    }
}

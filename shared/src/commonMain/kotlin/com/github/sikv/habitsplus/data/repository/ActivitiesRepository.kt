package com.github.sikv.habitsplus.data.repository

import com.github.sikv.habitsplus.data.model.ActivityModel

internal interface ActivitiesRepository {

    fun addActivity(activity: ActivityModel): Boolean
    fun updateActivity(activity: ActivityModel): Boolean

    // TODO: Add pagination?
    fun getActivities(): List<ActivityModel>
}

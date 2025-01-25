package com.github.sikv.habitsplus.data.repository

import com.github.sikv.habitsplus.data.model.ActivityModel

internal interface ActivitiesRepository {

    fun addActivity(activity: ActivityModel): Boolean
    fun updateActivity(activity: ActivityModel): Boolean
    fun getActivities(year: Int): List<ActivityModel>
}

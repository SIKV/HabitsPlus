package com.github.sikv.habitsplus.feature.activity.list

import com.github.sikv.habitsplus.data.model.ActivityModel

data class ActivityGroup(
    val monthNumber: Int,
    val activities: List<ActivityModel>
)

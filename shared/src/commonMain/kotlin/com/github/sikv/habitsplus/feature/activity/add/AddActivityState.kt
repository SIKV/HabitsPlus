package com.github.sikv.habitsplus.feature.activity.add

import com.github.sikv.habitsplus.DefaultConfig
import com.github.sikv.habitsplus.data.model.Timestamp
import com.github.sikv.habitsplus.store.StoreState

data class AddActivityState(
    val date: Timestamp = 0,
    val description: String = "",
    val descriptionMinLines: Int = DefaultConfig.ADD_ACTIVITY_DESCRIPTION_MIN_LINES,
    val descriptionMaxLength: Int = DefaultConfig.ADD_ACTIVITY_DESCRIPTION_MAX_LENGTH,
) : StoreState

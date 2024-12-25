package com.github.sikv.habitsplus.feature.activity.add

import com.github.sikv.habitsplus.data.model.Timestamp
import com.github.sikv.habitsplus.store.StoreState

data class AddActivityState(
    val date: Timestamp = 0,
    val description: String = ""
) : StoreState

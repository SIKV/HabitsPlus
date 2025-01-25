package com.github.sikv.habitsplus.feature.activity.list

import com.github.sikv.habitsplus.store.StoreState

data class ActivityListState(
    val isLoading: Boolean = false,
    val activities: List<ActivityGroup> = emptyList()
): StoreState

package com.github.sikv.habitsplus.feature.activity.list

import com.github.sikv.habitsplus.store.StoreState

data class ActivityListState(
    val isLoading: Boolean = false,
    val selectedYear: Int? = null,
    val activities: List<ActivityGroup> = emptyList(),
    val yearsFilter: Set<Int> = emptySet()
): StoreState

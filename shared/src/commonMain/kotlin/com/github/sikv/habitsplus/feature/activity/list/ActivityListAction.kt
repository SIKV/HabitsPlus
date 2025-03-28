package com.github.sikv.habitsplus.feature.activity.list

import com.github.sikv.habitsplus.store.Action

sealed class ActivityListAction : Action {

    data object Init : ActivityListAction()

    data class FetchAll(
        val year: Int
    ) : ActivityListAction()

    data class UpdateLoading(
        val isLoading: Boolean
    ) : ActivityListAction()

    data class UpdateList(
        val selectedYear: Int,
        val activities: List<ActivityGroup>,
    ) : ActivityListAction()

    data class UpdateYearsFilter(
        val yearsFilter: Set<Int>,
    ) : ActivityListAction()
}

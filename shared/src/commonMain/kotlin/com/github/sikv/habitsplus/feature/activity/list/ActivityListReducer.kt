package com.github.sikv.habitsplus.feature.activity.list

import com.github.sikv.habitsplus.store.Reducer

val activityListReducer: Reducer<ActivityListState, ActivityListAction> = { state, action ->
    when (action) {
        is ActivityListAction.UpdateLoading -> state.copy(
            isLoading = action.isLoading
        )
        is ActivityListAction.UpdateList -> state.copy(
            isLoading = false,
            selectedYear = action.selectedYear,
            activities = action.activities
        )
        is ActivityListAction.UpdateYearsFilter -> state.copy(
            yearsFilter = action.yearsFilter
        )
        else -> state
    }
}

package com.github.sikv.habitsplus.feature.activity.add

import com.github.sikv.habitsplus.store.Reducer

val addActivityReducer: Reducer<AddActivityState, AddActivityAction> = { state, action ->
    when (action) {
        is AddActivityAction.UpdateDate -> state.copy(
            date = action.date
        )
        is AddActivityAction.UpdateDescription -> state.copy(
            description = action.description
        )
        is AddActivityAction.Update -> state.copy(
            date = action.date,
            description = action.description
        )
        else -> state
    }
}

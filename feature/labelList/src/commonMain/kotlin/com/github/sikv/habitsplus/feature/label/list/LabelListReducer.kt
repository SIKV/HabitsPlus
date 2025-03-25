package com.github.sikv.habitsplus.feature.label.list

import com.github.sikv.habitsplus.store.Reducer

val labelListReducer: Reducer<LabelListState, LabelListAction> = { state, action ->
    when (action) {
        is LabelListAction.UpdateLoading -> state.copy(
            isLoading = action.isLoading
        )
        is LabelListAction.UpdateList -> state.copy(
            isLoading = false,
            labels = action.labels
        )
        else -> state
    }
}

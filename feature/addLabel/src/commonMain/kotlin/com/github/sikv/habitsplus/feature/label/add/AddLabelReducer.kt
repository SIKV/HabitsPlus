package com.github.sikv.habitsplus.feature.label.add

import com.github.sikv.habitsplus.store.Reducer

val addLabelReducer: Reducer<AddLabelState, AddLabelAction> = { state, action ->
    when (action) {
        is AddLabelAction.UpdateTitle -> state.copy(
            title = action.title
        )
        is AddLabelAction.UpdateColor -> state.copy(
            color = action.color
        )
        is AddLabelAction.Update -> state.copy(
            title = action.title,
            color = action.color
        )
        else -> state
    }
}

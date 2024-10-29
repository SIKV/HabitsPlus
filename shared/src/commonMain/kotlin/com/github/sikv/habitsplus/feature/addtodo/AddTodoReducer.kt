package com.github.sikv.habitsplus.feature.addtodo

import com.github.sikv.habitsplus.store.Reducer

val addTodoReducer: Reducer<AddTodoState, AddTodoAction> = { state, action ->
    when (action) {
        is AddTodoAction.UpdateTitle -> state.copy(
            title = action.title
        )
        else -> state
    }
}

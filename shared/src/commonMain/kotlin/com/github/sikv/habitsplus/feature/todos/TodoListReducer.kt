package com.github.sikv.habitsplus.feature.todos

import com.github.sikv.habitsplus.store.Reducer

val todoListReducer: Reducer<TodoListState, TodoListAction> = { state, action ->
    when (action) {
        is TodoListAction.UpdateLoading -> state.copy(
            isLoading = action.isLoading
        )
        is TodoListAction.UpdateList -> state.copy(
            isLoading = false,
            todos = action.todos
        )
        else -> state
    }
}
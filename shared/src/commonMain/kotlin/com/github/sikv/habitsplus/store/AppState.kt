package com.github.sikv.habitsplus.store

import com.github.sikv.habitsplus.feature.todos.TodoListState

data class AppState(
    val todoListState: TodoListState
): State {

    companion object {
        val emptyState = AppState(
            todoListState = TodoListState.emptyState
        )
    }
}

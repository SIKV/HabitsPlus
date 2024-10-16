package com.github.sikv.habitsplus.feature.todos

import com.github.sikv.habitsplus.store.State

data class TodoListState(
    val isLoading: Boolean,
    val todos: List<Todo>
): State {

    companion object {
        val emptyState = TodoListState(
            isLoading = false,
            todos = emptyList()
        )
    }
}

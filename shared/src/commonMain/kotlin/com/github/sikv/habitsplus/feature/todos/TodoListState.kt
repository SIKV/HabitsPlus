package com.github.sikv.habitsplus.feature.todos

import com.github.sikv.habitsplus.data.model.Todo
import com.github.sikv.habitsplus.store.StoreState

data class TodoListState(
    val isLoading: Boolean,
    val todos: List<Todo>
): StoreState {

    companion object {
        val emptyState = TodoListState(
            isLoading = false,
            todos = emptyList()
        )
    }
}

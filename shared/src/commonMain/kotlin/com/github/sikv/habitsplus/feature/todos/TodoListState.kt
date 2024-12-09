package com.github.sikv.habitsplus.feature.todos

import com.github.sikv.habitsplus.data.model.Todo
import com.github.sikv.habitsplus.data.model.TodoOrderBy
import com.github.sikv.habitsplus.store.StoreState

data class TodoListState(
    val isLoading: Boolean,
    val todos: List<Todo>,
    val orderByOptions: List<TodoOrderBy>,
    val orderBy: TodoOrderBy,
    val showCompleted: Boolean
): StoreState {

    companion object {
        val emptyState = TodoListState(
            isLoading = false,
            todos = emptyList(),
            orderByOptions = emptyList(),
            orderBy = TodoOrderBy.ADDED_AT_DESC,
            showCompleted = false
        )
    }
}

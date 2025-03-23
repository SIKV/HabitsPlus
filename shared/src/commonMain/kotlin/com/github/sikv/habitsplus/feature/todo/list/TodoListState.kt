package com.github.sikv.habitsplus.feature.todo.list

import com.github.sikv.habitsplus.data.todo.model.TodoModel
import com.github.sikv.habitsplus.data.todo.model.TodoOrderBy
import com.github.sikv.habitsplus.store.StoreState

data class TodoListState(
    val isLoading: Boolean = false,
    val todos: List<TodoModel> = emptyList(),
    val orderByOptions: List<TodoOrderBy> = emptyList(),
    val orderBy: TodoOrderBy = TodoOrderBy.ADDED_AT_DESC,
    val showCompleted: Boolean = false
): StoreState

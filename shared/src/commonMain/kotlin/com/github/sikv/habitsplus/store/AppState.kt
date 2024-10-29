package com.github.sikv.habitsplus.store

import com.github.sikv.habitsplus.feature.addtodo.AddTodoState
import com.github.sikv.habitsplus.feature.todos.TodoListState

data class AppState(
    val todoListState: TodoListState,
    val addTodoState: AddTodoState
): StoreState {

    companion object {
        val emptyState = AppState(
            todoListState = TodoListState.emptyState,
            addTodoState = AddTodoState.emptyState
        )
    }
}

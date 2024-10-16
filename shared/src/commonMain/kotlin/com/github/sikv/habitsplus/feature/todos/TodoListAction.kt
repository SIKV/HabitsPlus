package com.github.sikv.habitsplus.feature.todos

import com.github.sikv.habitsplus.store.Action

sealed class TodoListAction : Action {

    data object FetchAll : TodoListAction()

    data class UpdateLoading(
        val isLoading: Boolean
    ) : TodoListAction()

    data class UpdateList(
        val todos: List<Todo>
    ) : TodoListAction()
}

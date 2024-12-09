package com.github.sikv.habitsplus.feature.todos

import com.github.sikv.habitsplus.data.model.Todo
import com.github.sikv.habitsplus.data.model.TodoOrderBy
import com.github.sikv.habitsplus.store.Action

sealed class TodoListAction : Action {

    data object FetchAll : TodoListAction()

    data class UpdateLoading(
        val isLoading: Boolean
    ) : TodoListAction()

    data class UpdateList(
        val todos: List<Todo>,
        val orderByOptions: List<TodoOrderBy>? = null,
        val orderBy: TodoOrderBy? = null,
        val showCompleted: Boolean? = null,
    ) : TodoListAction()

    data class ToggleStatus(
        val todo: Todo
    ) : TodoListAction()

    data class UpdateOrderBy(
        val orderBy: TodoOrderBy
    ) : TodoListAction()

    data class UpdateShowCompleted(
        val showCompleted: Boolean
    ) : TodoListAction()
}

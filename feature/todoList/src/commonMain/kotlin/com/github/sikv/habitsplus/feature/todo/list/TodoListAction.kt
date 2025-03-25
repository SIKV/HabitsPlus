package com.github.sikv.habitsplus.feature.todo.list

import com.github.sikv.habitsplus.data.todo.model.TodoModel
import com.github.sikv.habitsplus.data.todo.model.TodoOrderBy
import com.github.sikv.habitsplus.store.Action

sealed class TodoListAction : Action {

    data object FetchAll : TodoListAction()

    data class UpdateLoading(
        val isLoading: Boolean
    ) : TodoListAction()

    data class UpdateList(
        val todos: List<TodoModel>,
        val orderByOptions: List<TodoOrderBy>? = null,
        val orderBy: TodoOrderBy? = null,
        val showCompleted: Boolean? = null,
    ) : TodoListAction()

    data class ToggleStatus(
        val todo: TodoModel
    ) : TodoListAction()

    data class UpdateOrderBy(
        val orderBy: TodoOrderBy
    ) : TodoListAction()

    data class UpdateShowCompleted(
        val showCompleted: Boolean
    ) : TodoListAction()
}

package com.github.sikv.habitsplus.feature.todos

import com.github.sikv.habitsplus.data.model.Todo
import com.github.sikv.habitsplus.data.model.TodoOrderBy
import com.github.sikv.habitsplus.store.Action

sealed class TodoListAction : Action {

    data class FetchAll(
        val orderBy: TodoOrderBy
    ) : TodoListAction()

    data class UpdateLoading(
        val isLoading: Boolean
    ) : TodoListAction()

    data class UpdateList(
        val todos: List<Todo>
    ) : TodoListAction()

    data class ToggleStatus(
        val todo: Todo
    ) : TodoListAction()
}

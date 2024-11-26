package com.github.sikv.habitsplus.data

import com.github.sikv.habitsplus.data.model.Todo
import com.github.sikv.habitsplus.data.model.TodoOrderBy
import com.github.sikv.habitsplus.data.model.TodoStatus

internal interface TodosRepository {

    // Note: [addedAtMs] property should be populated in repository implementation.
    fun addTodo(todo: Todo)

    // Note: [editedAtMs] property should be populated in repository implementation.
    fun updateTodo(todo: Todo): Boolean

    fun getAllTodos(orderBy: TodoOrderBy): List<Todo>
}

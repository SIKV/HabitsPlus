package com.github.sikv.habitsplus.data.repository

import com.github.sikv.habitsplus.data.model.Todo
import com.github.sikv.habitsplus.data.model.TodoOrderBy

internal interface TodosRepository {

    // Note: [addedAtMs] property should be populated in repository implementation.
    fun addTodo(todo: Todo)

    // Note: [editedAtMs] property should be populated in repository implementation.
    fun updateTodo(todo: Todo): Boolean

    fun getAllTodos(orderBy: TodoOrderBy, showCompleted: Boolean): List<Todo>
}

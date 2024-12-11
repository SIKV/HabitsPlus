package com.github.sikv.habitsplus.data.repository

import com.github.sikv.habitsplus.data.model.TodoModel
import com.github.sikv.habitsplus.data.model.TodoOrderBy

internal interface TodosRepository {

    // Note: [addedAtMs] property should be populated in repository implementation.
    fun addTodo(todo: TodoModel)

    // Note: [editedAtMs] property should be populated in repository implementation.
    fun updateTodo(todo: TodoModel): Boolean

    fun getAllTodos(orderBy: TodoOrderBy, showCompleted: Boolean): List<TodoModel>
}

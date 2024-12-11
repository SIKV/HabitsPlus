package com.github.sikv.habitsplus.data.repository

import com.github.sikv.habitsplus.data.model.TodoModel
import com.github.sikv.habitsplus.data.model.TodoOrderBy

internal interface TodosRepository {

    fun addTodo(todo: TodoModel)
    fun updateTodo(todo: TodoModel): Boolean
    fun getAllTodos(orderBy: TodoOrderBy, showCompleted: Boolean): List<TodoModel>
}

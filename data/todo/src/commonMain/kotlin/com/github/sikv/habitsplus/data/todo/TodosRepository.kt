package com.github.sikv.habitsplus.data.todo

import com.github.sikv.habitsplus.data.todo.model.TodoModel
import com.github.sikv.habitsplus.data.todo.model.TodoOrderBy

interface TodosRepository {
    fun addTodo(todo: TodoModel)
    fun updateTodo(todo: TodoModel): Boolean
    fun getAllTodos(orderBy: TodoOrderBy, showCompleted: Boolean): List<TodoModel>
}

package com.github.sikv.habitsplus.data

import com.github.sikv.habitsplus.data.model.Todo
import com.github.sikv.habitsplus.data.model.TodoOrderBy

internal interface TodosRepository {
    fun addTodo(todo: Todo)
    fun getAllTodos(orderBy: TodoOrderBy): List<Todo>
}

package com.github.sikv.habitsplus.data

import com.github.sikv.habitsplus.data.model.Todo

internal interface TodosRepository {

    fun addTodo(todo: Todo)
    fun getAllTodos(): List<Todo>
}

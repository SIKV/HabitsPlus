package com.github.sikv.habitsplus.data

import com.github.sikv.habitsplus.data.model.Todo
import com.github.sikv.habitsplus.database.Database

internal class TodosRepository(
    private val database: Database
) {

    fun addTodo(todo: Todo) {
        database.dbQuery.insertTodo(
            title = todo.title
        )
    }

    fun getAllTodos(): List<Todo> {
        return database.dbQuery.selectAllTodos(::mapTodo).executeAsList()
    }

    private fun mapTodo(
        title: String?
    ): Todo {
        return Todo(
            title = title ?: ""
        )
    }
}

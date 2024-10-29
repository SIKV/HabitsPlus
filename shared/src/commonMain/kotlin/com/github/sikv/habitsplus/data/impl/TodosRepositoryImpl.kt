package com.github.sikv.habitsplus.data.impl

import com.github.sikv.habitsplus.data.TodosRepository
import com.github.sikv.habitsplus.data.model.Todo
import com.github.sikv.habitsplus.database.Database

class TodosRepositoryImpl(
    private val database: Database
) : TodosRepository {

    override fun addTodo(todo: Todo) {
        database.dbQuery.insertTodo(
            title = todo.title
        )
    }

    override fun getAllTodos(): List<Todo> {
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

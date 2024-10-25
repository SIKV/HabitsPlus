package com.github.sikv.habitsplus.feature.todos

import com.github.sikv.habitsplus.database.Database
import kotlin.random.Random

internal class TodosRepository(
    private val database: Database
) {

    fun addTodo(todo: Todo) {
        database.dbQuery.insertTodo(
            title = todo.title
        )
    }

    fun getAllTodos(): List<Todo> {
        // TODO: For testing.
        addTodo(Todo("Todo${Random.nextInt()}"))

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

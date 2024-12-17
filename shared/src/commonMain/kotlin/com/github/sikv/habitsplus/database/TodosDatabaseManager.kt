package com.github.sikv.habitsplus.database

import com.github.sikv.habitsplus.database.todos.TodosDatabase

class TodosDatabaseManager(
    databaseDriverFactory: DatabaseDriverFactory
) {
    private val database = TodosDatabase(databaseDriverFactory.createTodosDriver())
    val dbQuery = database.todosDatabaseQueries
}

package com.github.sikv.habitsplus.data.todo

import com.github.sikv.habitsplus.database.todos.TodosDatabase

class TodosDatabaseManager(
    databaseDriverFactory: TodosDatabaseDriverFactory
) {
    private val database = TodosDatabase(databaseDriverFactory.createDriver())
    val dbQuery = database.todosDatabaseQueries
}

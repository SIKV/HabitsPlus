package com.github.sikv.habitsplus.database

class TodosDatabaseManager(
    databaseDriverFactory: DatabaseDriverFactory
) {
    private val database = TodosDatabase(databaseDriverFactory.createTodosDriver())
    val dbQuery = database.todosDatabaseQueries
}

package com.github.sikv.habitsplus.database

class Database(
    databaseDriverFactory: DatabaseDriverFactory
) {
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    val dbQuery = database.appDatabaseQueries
}

package com.github.sikv.habitsplus.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver

internal class IOSDatabaseDriverFactory : DatabaseDriverFactory {

    override fun createTodosDriver(): SqlDriver {
        return NativeSqliteDriver(TodosDatabase.Schema, "todos.db")
    }
}

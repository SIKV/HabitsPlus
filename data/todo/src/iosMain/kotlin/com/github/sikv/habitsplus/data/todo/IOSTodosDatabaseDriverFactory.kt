package com.github.sikv.habitsplus.data.todo

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.github.sikv.habitsplus.database.todos.TodosDatabase

class IOSTodosDatabaseDriverFactory : TodosDatabaseDriverFactory {

    override fun createDriver(): SqlDriver {
        return NativeSqliteDriver(TodosDatabase.Schema, "todos.db")
    }
}

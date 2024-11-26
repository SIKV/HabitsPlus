package com.github.sikv.habitsplus.database

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver

internal class AndroidDatabaseDriverFactory(
    private val context: Context
) : DatabaseDriverFactory  {

    override fun createTodosDriver(): SqlDriver {
        return AndroidSqliteDriver(TodosDatabase.Schema, context, "todos.db")
    }
}

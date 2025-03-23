package com.github.sikv.habitsplus.data.todo

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.github.sikv.habitsplus.database.todos.TodosDatabase

class AndroidTodosDatabaseDriverFactory(
    private val context: Context
) : TodosDatabaseDriverFactory {

    override fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(TodosDatabase.Schema, context, "todos.db")
    }
}

package com.github.sikv.habitsplus.database

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.github.sikv.habitsplus.database.activities.ActivitiesDatabase
import com.github.sikv.habitsplus.database.todos.TodosDatabase

internal class AndroidDatabaseDriverFactory(
    private val context: Context
) : DatabaseDriverFactory {

    override fun createTodosDriver(): SqlDriver {
        return AndroidSqliteDriver(TodosDatabase.Schema, context, "todos.db")
    }

    override fun createActivitiesDriver(): SqlDriver {
        return AndroidSqliteDriver(ActivitiesDatabase.Schema, context, "activities.db")
    }
}

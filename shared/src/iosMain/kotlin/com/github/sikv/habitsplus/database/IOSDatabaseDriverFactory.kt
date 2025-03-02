package com.github.sikv.habitsplus.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.github.sikv.habitsplus.database.activities.ActivitiesDatabase
import com.github.sikv.habitsplus.database.labels.LabelsDatabase
import com.github.sikv.habitsplus.database.todos.TodosDatabase

internal class IOSDatabaseDriverFactory : DatabaseDriverFactory {

    override fun createTodosDriver(): SqlDriver {
        return NativeSqliteDriver(TodosDatabase.Schema, "todos.db")
    }

    override fun createActivitiesDriver(): SqlDriver {
        return NativeSqliteDriver(ActivitiesDatabase.Schema, "activities.db")
    }

    override fun createLabelsDriver(): SqlDriver {
        return NativeSqliteDriver(LabelsDatabase.Schema, "labels.db")
    }
}

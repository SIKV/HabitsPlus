package com.github.sikv.habitsplus.database

import app.cash.sqldelight.db.SqlDriver

interface DatabaseDriverFactory {
    fun createTodosDriver(): SqlDriver
    fun createActivitiesDriver(): SqlDriver
}

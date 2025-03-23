package com.github.sikv.habitsplus.data.todo

import app.cash.sqldelight.db.SqlDriver

interface TodosDatabaseDriverFactory {
    fun createDriver(): SqlDriver
}

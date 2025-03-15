package com.github.sikv.habitsplus.data.label

import app.cash.sqldelight.db.SqlDriver

interface LabelsDatabaseDriverFactory {
    fun createDriver(): SqlDriver
}

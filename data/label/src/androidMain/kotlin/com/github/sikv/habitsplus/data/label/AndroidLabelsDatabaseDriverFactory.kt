package com.github.sikv.habitsplus.data.label

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.github.sikv.habitsplus.database.labels.LabelsDatabase

class AndroidLabelsDatabaseDriverFactory(
    private val context: Context
) : LabelsDatabaseDriverFactory {

    override fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(LabelsDatabase.Schema, context, "labels.db")
    }
}

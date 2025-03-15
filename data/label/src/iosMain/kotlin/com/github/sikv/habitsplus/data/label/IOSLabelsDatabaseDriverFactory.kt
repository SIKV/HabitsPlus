package com.github.sikv.habitsplus.data.label

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.github.sikv.habitsplus.database.labels.LabelsDatabase

class IOSLabelsDatabaseDriverFactory : LabelsDatabaseDriverFactory {

    override fun createDriver(): SqlDriver {
        return NativeSqliteDriver(LabelsDatabase.Schema, "labels.db")
    }
}

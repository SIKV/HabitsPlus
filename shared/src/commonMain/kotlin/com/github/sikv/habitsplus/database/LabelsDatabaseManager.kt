package com.github.sikv.habitsplus.database

import com.github.sikv.habitsplus.database.labels.LabelsDatabase

class LabelsDatabaseManager(
    databaseDriverFactory: DatabaseDriverFactory
) {
    private val database = LabelsDatabase(databaseDriverFactory.createLabelsDriver())
    val dbQuery = database.labelsDatabaseQueries
}

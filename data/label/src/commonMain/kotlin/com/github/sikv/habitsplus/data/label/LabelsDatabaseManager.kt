package com.github.sikv.habitsplus.data.label

import com.github.sikv.habitsplus.database.labels.LabelsDatabase

class LabelsDatabaseManager(
    databaseDriverFactory: LabelsDatabaseDriverFactory
) {
    private val database = LabelsDatabase(databaseDriverFactory.createDriver())
    val dbQuery = database.labelsDatabaseQueries
}

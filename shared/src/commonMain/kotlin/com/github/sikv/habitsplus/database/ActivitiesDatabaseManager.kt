package com.github.sikv.habitsplus.database

import com.github.sikv.habitsplus.database.activities.ActivitiesDatabase

class ActivitiesDatabaseManager(
    databaseDriverFactory: DatabaseDriverFactory
) {
    private val database = ActivitiesDatabase(databaseDriverFactory.createActivitiesDriver())
    val dbQuery = database.activitiesDatabaseQueries
}

package com.github.sikv.habitsplus.di

import com.github.sikv.habitsplus.database.ActivitiesDatabaseManager
import com.github.sikv.habitsplus.database.AndroidDatabaseDriverFactory
import com.github.sikv.habitsplus.database.TodosDatabaseManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single<TodosDatabaseManager> {
        TodosDatabaseManager(
            databaseDriverFactory = AndroidDatabaseDriverFactory(androidContext())
        )
    }
    single<ActivitiesDatabaseManager> {
        ActivitiesDatabaseManager(
            databaseDriverFactory = AndroidDatabaseDriverFactory(androidContext())
        )
    }
}

package com.github.sikv.habitsplus.di

import com.github.sikv.habitsplus.database.ActivitiesDatabaseManager
import com.github.sikv.habitsplus.database.IOSDatabaseDriverFactory
import com.github.sikv.habitsplus.database.TodosDatabaseManager
import org.koin.dsl.module

val databaseModule = module {
    single<TodosDatabaseManager> {
        TodosDatabaseManager(
            databaseDriverFactory = IOSDatabaseDriverFactory()
        )
    }
    single<ActivitiesDatabaseManager> {
        ActivitiesDatabaseManager(
            databaseDriverFactory = IOSDatabaseDriverFactory()
        )
    }
}

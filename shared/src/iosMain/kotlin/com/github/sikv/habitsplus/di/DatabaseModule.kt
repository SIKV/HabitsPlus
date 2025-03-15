package com.github.sikv.habitsplus.di

import com.github.sikv.habitsplus.data.label.IOSLabelsDatabaseDriverFactory
import com.github.sikv.habitsplus.data.label.LabelsDatabaseManager
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
    single<LabelsDatabaseManager> {
        LabelsDatabaseManager(
            databaseDriverFactory = IOSLabelsDatabaseDriverFactory()
        )
    }
}

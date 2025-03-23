package com.github.sikv.habitsplus.di

import com.github.sikv.habitsplus.data.label.IOSLabelsDatabaseDriverFactory
import com.github.sikv.habitsplus.data.label.LabelsDatabaseManager
import com.github.sikv.habitsplus.data.todo.IOSTodosDatabaseDriverFactory
import com.github.sikv.habitsplus.data.todo.TodosDatabaseManager
import com.github.sikv.habitsplus.database.ActivitiesDatabaseManager
import com.github.sikv.habitsplus.database.IOSDatabaseDriverFactory
import org.koin.dsl.module

val databaseModule = module {
    single<TodosDatabaseManager> {
        TodosDatabaseManager(
            databaseDriverFactory = IOSTodosDatabaseDriverFactory()
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

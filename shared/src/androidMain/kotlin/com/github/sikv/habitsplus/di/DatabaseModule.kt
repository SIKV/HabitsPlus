package com.github.sikv.habitsplus.di

import com.github.sikv.habitsplus.data.label.AndroidLabelsDatabaseDriverFactory
import com.github.sikv.habitsplus.data.label.LabelsDatabaseManager
import com.github.sikv.habitsplus.data.todo.AndroidTodosDatabaseDriverFactory
import com.github.sikv.habitsplus.data.todo.TodosDatabaseManager
import com.github.sikv.habitsplus.database.ActivitiesDatabaseManager
import com.github.sikv.habitsplus.database.AndroidDatabaseDriverFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single<TodosDatabaseManager> {
        TodosDatabaseManager(
            databaseDriverFactory = AndroidTodosDatabaseDriverFactory(androidContext())
        )
    }
    single<ActivitiesDatabaseManager> {
        ActivitiesDatabaseManager(
            databaseDriverFactory = AndroidDatabaseDriverFactory(androidContext())
        )
    }
    single<LabelsDatabaseManager> {
        LabelsDatabaseManager(
            databaseDriverFactory = AndroidLabelsDatabaseDriverFactory(androidContext())
        )
    }
}

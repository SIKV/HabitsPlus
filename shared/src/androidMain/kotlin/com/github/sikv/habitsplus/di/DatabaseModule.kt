package com.github.sikv.habitsplus.di

import com.github.sikv.habitsplus.database.AndroidDatabaseDriverFactory
import com.github.sikv.habitsplus.database.Database
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single<Database> {
        Database(
            databaseDriverFactory = AndroidDatabaseDriverFactory(androidContext())
        )
    }
}

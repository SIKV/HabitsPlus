package com.github.sikv.habitsplus.di

import com.github.sikv.habitsplus.database.Database
import com.github.sikv.habitsplus.database.IOSDatabaseDriverFactory
import org.koin.dsl.module

val databaseModule = module {
    single<Database> {
        Database(
            databaseDriverFactory = IOSDatabaseDriverFactory()
        )
    }
}

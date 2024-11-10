package com.github.sikv.habitsplus.di

import com.github.sikv.habitsplus.data.local.TodosLocalDataSource
import com.github.sikv.habitsplus.data.local.impl.TodosLocalDataSourceImpl
import org.koin.dsl.module

val dataSourceModule = module {
    single<TodosLocalDataSource> {
        TodosLocalDataSourceImpl(
            database = get()
        )
    }
}

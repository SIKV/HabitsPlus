package com.github.sikv.habitsplus.di

import com.github.sikv.habitsplus.data.source.TodosLocalDataSource
import com.github.sikv.habitsplus.data.source.impl.TodosLocalDataSourceImpl
import org.koin.dsl.module

val dataSourceModule = module {
    single<TodosLocalDataSource> {
        TodosLocalDataSourceImpl(
            database = get()
        )
    }
}

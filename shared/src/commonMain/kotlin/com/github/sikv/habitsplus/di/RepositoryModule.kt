package com.github.sikv.habitsplus.di

import com.github.sikv.habitsplus.feature.todos.TodosRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<TodosRepository> {
        TodosRepository(
            database = get()
        )
    }
}

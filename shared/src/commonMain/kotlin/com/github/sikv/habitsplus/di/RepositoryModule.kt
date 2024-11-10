package com.github.sikv.habitsplus.di

import com.github.sikv.habitsplus.data.TodosRepository
import com.github.sikv.habitsplus.data.impl.TodosRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<TodosRepository> {
        TodosRepositoryImpl(
            todosLocalDataSource = get()
        )
    }
}

package com.github.sikv.habitsplus.di

import com.github.sikv.habitsplus.data.repository.ActivitiesRepository
import com.github.sikv.habitsplus.data.repository.TodosRepository
import com.github.sikv.habitsplus.data.repository.impl.ActivitiesRepositoryImpl
import com.github.sikv.habitsplus.data.repository.impl.TodosRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<TodosRepository> {
        TodosRepositoryImpl(
            todosLocalDataSource = get(),
            dateTimeUtils = get()
        )
    }
    single<ActivitiesRepository> {
        ActivitiesRepositoryImpl(
            activitiesLocalDataSource = get(),
            dateTimeUtils = get()
        )
    }
}

package com.github.sikv.habitsplus.di

import com.github.sikv.habitsplus.data.repository.ActivitiesRepository
import com.github.sikv.habitsplus.data.label.LabelsRepository
import com.github.sikv.habitsplus.data.repository.TodosRepository
import com.github.sikv.habitsplus.data.repository.impl.ActivitiesRepositoryImpl
import com.github.sikv.habitsplus.data.label.impl.LabelsRepositoryImpl
import com.github.sikv.habitsplus.data.repository.impl.TodosRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<TodosRepository> {
        TodosRepositoryImpl(
            todosDataSource = get(),
            dateTimeUtils = get()
        )
    }
    single<ActivitiesRepository> {
        ActivitiesRepositoryImpl(
            activitiesDataSource = get(),
            dateTimeUtils = get()
        )
    }
    single<LabelsRepository> {
        LabelsRepositoryImpl(
            labelsDataSource = get()
        )
    }
}

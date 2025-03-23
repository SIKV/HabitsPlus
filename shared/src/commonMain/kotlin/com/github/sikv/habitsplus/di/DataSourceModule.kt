package com.github.sikv.habitsplus.di

import com.github.sikv.habitsplus.data.source.ActivitiesDataSource
import com.github.sikv.habitsplus.data.label.LabelsDataSource
import com.github.sikv.habitsplus.data.todo.TodosDataSource
import com.github.sikv.habitsplus.data.source.impl.ActivitiesLocalDataSourceImpl
import com.github.sikv.habitsplus.data.label.impl.LabelsLocalDataSourceImpl
import com.github.sikv.habitsplus.data.todo.impl.TodosLocalDataSourceImpl
import org.koin.dsl.module

val dataSourceModule = module {
    single<TodosDataSource> {
        TodosLocalDataSourceImpl(
            database = get()
        )
    }
    single<ActivitiesDataSource> {
        ActivitiesLocalDataSourceImpl(
            database = get(),
            dateTimeUtils = get()
        )
    }
    single<LabelsDataSource> {
        LabelsLocalDataSourceImpl(
            database = get()
        )
    }
}

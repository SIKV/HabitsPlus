package com.github.sikv.habitsplus.di

import com.github.sikv.habitsplus.data.source.ActivitiesLocalDataSource
import com.github.sikv.habitsplus.data.source.LabelsLocalDataSource
import com.github.sikv.habitsplus.data.source.TodosLocalDataSource
import com.github.sikv.habitsplus.data.source.impl.ActivitiesLocalDataSourceImpl
import com.github.sikv.habitsplus.data.source.impl.LabelsLocalDataSourceImpl
import com.github.sikv.habitsplus.data.source.impl.TodosLocalDataSourceImpl
import org.koin.dsl.module

val dataSourceModule = module {
    single<TodosLocalDataSource> {
        TodosLocalDataSourceImpl(
            database = get()
        )
    }
    single<ActivitiesLocalDataSource> {
        ActivitiesLocalDataSourceImpl(
            database = get(),
            dateTimeUtils = get()
        )
    }
    single<LabelsLocalDataSource> {
        LabelsLocalDataSourceImpl(
            database = get()
        )
    }
}

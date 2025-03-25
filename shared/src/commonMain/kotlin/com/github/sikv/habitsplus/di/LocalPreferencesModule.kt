package com.github.sikv.habitsplus.di

import com.github.sikv.habitsplus.data.todo.TodosLocalPreferences
import com.github.sikv.habitsplus.data.todo.impl.TodosLocalPreferencesImpl
import org.koin.dsl.module

val localPreferencesModule = module {
    single<TodosLocalPreferences> {
        TodosLocalPreferencesImpl()
    }
}

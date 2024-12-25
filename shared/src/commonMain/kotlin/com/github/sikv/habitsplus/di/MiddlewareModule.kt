package com.github.sikv.habitsplus.di

import com.github.sikv.habitsplus.feature.activity.add.ActivityValidator
import com.github.sikv.habitsplus.feature.activity.add.AddActivityMiddleware
import com.github.sikv.habitsplus.feature.addtodo.AddTodoMiddleware
import com.github.sikv.habitsplus.feature.addtodo.TodoValidator
import com.github.sikv.habitsplus.feature.todos.TodoListMiddleware
import org.koin.dsl.module

val middlewareModule = module {
    single<TodoListMiddleware> {
        TodoListMiddleware(
            todosRepository = get(),
            localPreferences = get(),
            dateTimeUtils = get()
        )
    }
    single<AddTodoMiddleware> {
        AddTodoMiddleware(
            todosRepository = get(),
            validator = TodoValidator()
        )
    }
    single<AddActivityMiddleware> {
        AddActivityMiddleware(
            activitiesRepository = get(),
            validator = ActivityValidator(),
            dateTimeUtils = get()
        )
    }
}

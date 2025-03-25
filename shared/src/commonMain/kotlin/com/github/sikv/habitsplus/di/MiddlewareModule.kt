package com.github.sikv.habitsplus.di

import com.github.sikv.habitsplus.feature.activity.add.ActivityValidator
import com.github.sikv.habitsplus.feature.activity.add.AddActivityMiddleware
import com.github.sikv.habitsplus.feature.activity.list.ActivityListMiddleware
import com.github.sikv.habitsplus.feature.label.add.AddLabelMiddleware
import com.github.sikv.habitsplus.feature.label.add.LabelValidator
import com.github.sikv.habitsplus.feature.label.list.LabelListMiddleware
import com.github.sikv.habitsplus.feature.todo.add.AddTodoMiddleware
import com.github.sikv.habitsplus.feature.todo.add.TodoValidator
import com.github.sikv.habitsplus.feature.todo.list.TodoListMiddleware
import org.koin.dsl.module

val middlewareModule = module {
    single<TodoListMiddleware> {
        TodoListMiddleware(
            todosRepository = get(),
            todosLocalPreferences = get(),
            dateTimeUtils = get()
        )
    }
    single<AddTodoMiddleware> {
        AddTodoMiddleware(
            todosRepository = get(),
            validator = TodoValidator()
        )
    }
    single<ActivityListMiddleware> {
        ActivityListMiddleware(
            activitiesRepository = get(),
            dateTimeUtils = get()
        )
    }
    single<AddActivityMiddleware> {
        AddActivityMiddleware(
            activitiesRepository = get(),
            validator = ActivityValidator(),
            dateTimeUtils = get()
        )
    }
    single<LabelListMiddleware> {
        LabelListMiddleware(
            labelsRepository = get()
        )
    }
    single<AddLabelMiddleware> {
        AddLabelMiddleware(
            labelsRepository = get(),
            validator = LabelValidator()
        )
    }
}

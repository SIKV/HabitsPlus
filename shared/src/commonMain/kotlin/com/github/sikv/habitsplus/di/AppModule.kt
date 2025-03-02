package com.github.sikv.habitsplus.di

import com.github.sikv.habitsplus.feature.activity.add.AddActivityMiddleware
import com.github.sikv.habitsplus.feature.activity.list.ActivityListMiddleware
import com.github.sikv.habitsplus.feature.label.add.AddLabelMiddleware
import com.github.sikv.habitsplus.feature.label.list.LabelListMiddleware
import com.github.sikv.habitsplus.feature.todo.add.AddTodoMiddleware
import com.github.sikv.habitsplus.feature.todo.list.TodoListMiddleware
import com.github.sikv.habitsplus.store.AppState
import com.github.sikv.habitsplus.store.AppStore
import com.github.sikv.habitsplus.store.Store
import com.github.sikv.habitsplus.store.appReducer
import com.github.sikv.habitsplus.util.DateTimeUtils
import com.github.sikv.habitsplus.util.DateTimeUtilsImpl
import org.koin.dsl.module

val appModule = module {
    single<AppStore> {
        val todoListMiddleware: TodoListMiddleware = get()
        val addTodoMiddleware: AddTodoMiddleware = get()

        val activityListMiddleware: ActivityListMiddleware = get()
        val addActivityMiddleware: AddActivityMiddleware = get()

        val labelListMiddleware: LabelListMiddleware = get()
        val addLabelMiddleware: AddLabelMiddleware = get()

        Store(
            initialState = AppState(),
            reducer = appReducer,
            middlewares = listOf(
                todoListMiddleware,
                addTodoMiddleware,
                activityListMiddleware,
                addActivityMiddleware,
                labelListMiddleware,
                addLabelMiddleware
            )
        )
    }

    single<DateTimeUtils> {
        DateTimeUtilsImpl()
    }
}

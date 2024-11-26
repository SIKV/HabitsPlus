package com.github.sikv.habitsplus.di

import com.github.sikv.habitsplus.feature.addtodo.AddTodoMiddleware
import com.github.sikv.habitsplus.feature.todos.TodoListMiddleware
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

        Store(
            initialState = AppState.emptyState,
            reducer = appReducer,
            middlewares = listOf(
                todoListMiddleware,
                addTodoMiddleware
            )
        )
    }

    single<DateTimeUtils> {
        DateTimeUtilsImpl()
    }
}

package com.github.sikv.habitsplus.di

import com.github.sikv.habitsplus.feature.todos.TodoListMiddleware
import org.koin.dsl.module

val middlewareModule = module {
    single<TodoListMiddleware> {
        TodoListMiddleware()
    }
}

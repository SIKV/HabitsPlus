package com.github.sikv.habitsplus.di

import com.github.sikv.habitsplus.feature.addtodo.AddTodoMiddleware
import com.github.sikv.habitsplus.feature.todos.TodoListMiddleware
import org.koin.dsl.module

val middlewareModule = module {
    single<TodoListMiddleware> {
        TodoListMiddleware(
            todosRepository = get(),
            localPreferences = get()
        )
    }
    single<AddTodoMiddleware> {
        AddTodoMiddleware(
            todosRepository = get()
        )
    }
}

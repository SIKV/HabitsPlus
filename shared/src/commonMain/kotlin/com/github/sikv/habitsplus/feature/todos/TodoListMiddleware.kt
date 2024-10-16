package com.github.sikv.habitsplus.feature.todos

import com.github.sikv.habitsplus.store.Middleware
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

val todoListMiddleware: Middleware = { _, action, dispatcher ->
    withContext(Dispatchers.IO) {
        when (action) {
            TodoListAction.FetchAll -> {
                dispatcher(TodoListAction.UpdateLoading(true))

                // TODO: Fetch the real data.
                val testTodos = listOf(
                    Todo("Todo 1"),
                    Todo("Todo 2"),
                    Todo("Todo 3")
                )
                delay(3000)

                dispatcher(TodoListAction.UpdateList(testTodos))
            }
        }
    }
}

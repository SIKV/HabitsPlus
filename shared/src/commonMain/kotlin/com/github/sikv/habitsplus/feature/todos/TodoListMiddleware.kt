package com.github.sikv.habitsplus.feature.todos

import com.github.sikv.habitsplus.store.Action
import com.github.sikv.habitsplus.store.Dispatcher
import com.github.sikv.habitsplus.store.Middleware
import com.github.sikv.habitsplus.store.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

internal class TodoListMiddleware(
    private val todosRepository: TodosRepository
) : Middleware {
    override suspend fun invoke(state: State, action: Action, dispatcher: Dispatcher) {
        withContext(Dispatchers.IO) {
            when (action) {
                TodoListAction.FetchAll -> {
                    dispatcher(TodoListAction.UpdateLoading(true))
                    val todos = todosRepository.getAllTodos()
                    dispatcher(TodoListAction.UpdateList(todos))
                }
            }
        }
    }
}

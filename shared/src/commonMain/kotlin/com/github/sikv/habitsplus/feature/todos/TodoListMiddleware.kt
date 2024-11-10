package com.github.sikv.habitsplus.feature.todos

import com.github.sikv.habitsplus.data.TodosRepository
import com.github.sikv.habitsplus.store.Action
import com.github.sikv.habitsplus.store.AppMiddleware
import com.github.sikv.habitsplus.store.AppState
import com.github.sikv.habitsplus.store.Dispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

internal class TodoListMiddleware(
    private val todosRepository: TodosRepository
) : AppMiddleware {

    override suspend fun invoke(state: AppState, action: Action, dispatcher: Dispatcher) {
        withContext(Dispatchers.IO) {
            when (action) {
                is TodoListAction.FetchAll -> {
                    dispatcher(TodoListAction.UpdateLoading(true))
                    val todos = todosRepository.getAllTodos(action.orderBy)
                    dispatcher(TodoListAction.UpdateList(todos))
                }
            }
        }
    }
}

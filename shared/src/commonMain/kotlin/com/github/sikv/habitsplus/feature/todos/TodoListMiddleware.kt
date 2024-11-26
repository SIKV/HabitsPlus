package com.github.sikv.habitsplus.feature.todos

import com.github.sikv.habitsplus.data.TodosRepository
import com.github.sikv.habitsplus.data.model.TodoStatus
import com.github.sikv.habitsplus.store.Action
import com.github.sikv.habitsplus.store.AppMiddleware
import com.github.sikv.habitsplus.store.AppState
import com.github.sikv.habitsplus.store.Dispatcher
import com.github.sikv.habitsplus.util.TimeUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

internal class TodoListMiddleware(
    private val todosRepository: TodosRepository
) : AppMiddleware {

    override suspend fun invoke(state: AppState, action: Action, dispatcher: Dispatcher) {
        withContext(Dispatchers.IO) {
            when (action) {
                is TodoListAction.FetchAll -> handleFetchAllAction(action, dispatcher)
                is TodoListAction.ToggleStatus -> handleToggleStatusAction(
                    state.todoListState,
                    action,
                    dispatcher
                )
            }
        }
    }

    private fun handleFetchAllAction(action: TodoListAction.FetchAll, dispatcher: Dispatcher) {
        dispatcher(TodoListAction.UpdateLoading(true))
        val todos = todosRepository.getAllTodos(action.orderBy)
        dispatcher(TodoListAction.UpdateList(todos))
    }

    private fun handleToggleStatusAction(state: TodoListState, action: TodoListAction.ToggleStatus, dispatcher: Dispatcher) {
        val updatedStatus = when (action.todo.status) {
            TodoStatus.Todo -> TodoStatus.Done(doneAtMs = TimeUtils.currentTimeMillis())
            is TodoStatus.Done -> TodoStatus.Todo
        }

        val updatedTodo = action.todo.copy(
            status = updatedStatus
        )

        val isUpdated = todosRepository.updateTodo(updatedTodo)

        // Manually update status for the given item.
        val updatedTodos = state.todos.map { todo ->
            if (todo.id == action.todo.id) {
                todo.copy(status = updatedStatus)
            } else {
                todo
            }
        }

        // Emit updated list.
        dispatcher(TodoListAction.UpdateList(updatedTodos))

        // TODO: Emit error if [isUpdated] is false.
    }
}

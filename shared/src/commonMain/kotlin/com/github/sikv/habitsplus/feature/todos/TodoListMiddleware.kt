package com.github.sikv.habitsplus.feature.todos

import com.github.sikv.habitsplus.data.model.TodoStatus
import com.github.sikv.habitsplus.data.preferences.LocalPreferences
import com.github.sikv.habitsplus.data.repository.TodosRepository
import com.github.sikv.habitsplus.store.Action
import com.github.sikv.habitsplus.store.AppMiddleware
import com.github.sikv.habitsplus.store.AppState
import com.github.sikv.habitsplus.store.Dispatcher
import com.github.sikv.habitsplus.util.DateTimeUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

internal class TodoListMiddleware(
    private val todosRepository: TodosRepository,
    private val localPreferences: LocalPreferences,
    private val dateTimeUtils: DateTimeUtils
) : AppMiddleware {

    override suspend fun invoke(state: AppState, action: Action, dispatcher: Dispatcher) {
        withContext(Dispatchers.IO) {
            when (action) {
                is TodoListAction.FetchAll -> handleFetchAllAction(dispatcher)
                is TodoListAction.ToggleStatus -> handleToggleStatusAction(
                    state.todoListState,
                    action,
                    dispatcher
                )
                is TodoListAction.UpdateOrderBy -> handleUpdateOrderByAction(
                    action,
                    dispatcher
                )
                is TodoListAction.UpdateShowCompleted -> handleUpdateShowCompletedAction(
                    action,
                    dispatcher
                )
            }
        }
    }

    private fun handleFetchAllAction(dispatcher: Dispatcher) {
        updateList(dispatcher)
    }

    private fun handleToggleStatusAction(state: TodoListState, action: TodoListAction.ToggleStatus, dispatcher: Dispatcher) {
        val updatedStatus = when (action.todo.status) {
            TodoStatus.Todo -> TodoStatus.Done(doneAtMs = dateTimeUtils.currentTimestamp())
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

    private fun handleUpdateOrderByAction(action: TodoListAction.UpdateOrderBy, dispatcher: Dispatcher) {
        localPreferences.setTodoListOrderBy(action.orderBy)
        updateList(dispatcher)
    }

    private fun handleUpdateShowCompletedAction(action: TodoListAction.UpdateShowCompleted, dispatcher: Dispatcher) {
        localPreferences.setTodoListShowCompleted(action.showCompleted)
        updateList(dispatcher)
    }

    private fun updateList(dispatcher: Dispatcher) {
        val orderByOptions = localPreferences.getTodoListOrderByOptions()
        val orderBy = localPreferences.getTodoListOrderBy()
        val showCompleted = localPreferences.getTodoListShowCompleted()

        dispatcher(TodoListAction.UpdateLoading(true))

        val todos = todosRepository.getAllTodos(orderBy, showCompleted)

        dispatcher(TodoListAction.UpdateList(
            todos = todos,
            orderByOptions = orderByOptions,
            orderBy = orderBy,
            showCompleted = showCompleted
        ))
    }
}

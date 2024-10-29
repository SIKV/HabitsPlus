package com.github.sikv.habitsplus.feature.todos

import com.github.sikv.habitsplus.data.model.Todo
import com.github.sikv.habitsplus.feature.data.MockTodosRepository
import com.github.sikv.habitsplus.store.AppState
import com.github.sikv.habitsplus.store.Dispatcher
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TodoListMiddlewareTest {

    @Test
    fun invoke_dispatchesUpdateLoadingAndUpdateListActions_whenFetchAllActionReceived() = runTest {
        // GIVEN
        val mockTodosRepository = MockTodosRepository()

        val middleware = TodoListMiddleware(
            todosRepository = mockTodosRepository
        )

        val appState = AppState.emptyState
        val fetchAllAction = TodoListAction.FetchAll

        var updateLoadingActionReceived = false
        var updateLoadingActionIsLoading = false

        var updateListActionReceived = false
        var updateListActionTodos: List<Todo> = listOf()

        val dispatcher: Dispatcher = { action ->
            if (action is TodoListAction.UpdateLoading) {
                updateLoadingActionReceived = true
                updateLoadingActionIsLoading = action.isLoading
            } else if (action is TodoListAction.UpdateList) {
                updateListActionReceived = true
                updateListActionTodos = action.todos
            }
        }

        // WHEN
        middleware.invoke(appState, fetchAllAction, dispatcher)

        // THEN
        mockTodosRepository.verifyGetAllTodosCalled()

        assertTrue(updateLoadingActionReceived)
        assertEquals(true, updateLoadingActionIsLoading)

        assertTrue(updateListActionReceived)
        assertEquals(mockTodosRepository.testTodos, updateListActionTodos)
    }
}

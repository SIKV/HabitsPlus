package com.github.sikv.habitsplus.feature.todos

import com.github.sikv.habitsplus.data.model.Todo
import com.github.sikv.habitsplus.data.model.TodoOrderBy
import com.github.sikv.habitsplus.data.preferences.LocalPreferences
import com.github.sikv.habitsplus.data.repository.TodosRepository
import com.github.sikv.habitsplus.store.AppState
import com.github.sikv.habitsplus.store.Dispatcher
import com.github.sikv.habitsplus.util.testTodos
import kotlinx.coroutines.test.runTest
import org.kodein.mock.Mocker
import org.kodein.mock.UsesMocks
import org.kodein.mock.generated.mock
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@UsesMocks(TodosRepository::class, LocalPreferences::class)
class TodoListMiddlewareTest {

    @Test
    fun invoke_dispatchesUpdateLoadingAndUpdateListActions_whenFetchAllActionReceived() = runTest {
        // GIVEN
        val mocker = Mocker()

        val mockTodosRepository = mocker.mock<TodosRepository>()
        mocker.every { mockTodosRepository.getAllTodos(isAny(), isAny()) } returns testTodos

        val mockLocalPreferences = mocker.mock<LocalPreferences>()
        mocker.every { mockLocalPreferences.getTodoListOrderByOptions() } returns TodoOrderBy.entries
        mocker.every { mockLocalPreferences.getTodoListOrderBy() } returns TodoOrderBy.ADDED_AT_ASC
        mocker.every { mockLocalPreferences.getTodoListShowCompleted() } returns true

        val middleware = TodoListMiddleware(
            todosRepository = mockTodosRepository,
            localPreferences = mockLocalPreferences
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
        mocker.verify {
            mockLocalPreferences.getTodoListOrderByOptions()
            mockLocalPreferences.getTodoListOrderBy()
            mockLocalPreferences.getTodoListShowCompleted()

            mockTodosRepository.getAllTodos(
                orderBy = TodoOrderBy.ADDED_AT_ASC,
                showCompleted = true
            )
        }

        assertTrue(updateLoadingActionReceived)
        assertEquals(true, updateLoadingActionIsLoading)

        assertTrue(updateListActionReceived)

        val expectedTodos = testTodos
        assertEquals(expectedTodos, updateListActionTodos)
    }
}

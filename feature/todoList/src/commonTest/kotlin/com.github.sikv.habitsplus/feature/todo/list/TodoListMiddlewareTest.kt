package com.github.sikv.habitsplus.feature.todo.list

import com.github.sikv.habitsplus.data.common.DateTimeUtils
import com.github.sikv.habitsplus.data.todo.TodosLocalPreferences
import com.github.sikv.habitsplus.data.todo.TodosRepository
import com.github.sikv.habitsplus.data.todo.model.TodoModel
import com.github.sikv.habitsplus.data.todo.model.TodoOrderBy
import com.github.sikv.habitsplus.store.Dispatcher
import kotlinx.coroutines.test.runTest
import org.kodein.mock.Mocker
import org.kodein.mock.UsesMocks
import org.kodein.mock.generated.mock
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@UsesMocks(TodosRepository::class, TodosLocalPreferences::class, DateTimeUtils::class)
class TodoListMiddlewareTest {

    @Test
    fun invoke_dispatchesUpdateLoadingAndUpdateListActions_whenFetchAllActionReceived() = runTest {
        // GIVEN
        val mocker = Mocker()

        val mockTodosRepository = mocker.mock<TodosRepository>()
        mocker.every { mockTodosRepository.getAllTodos(isAny(), isAny()) } returns testTodos

        val mockLocalPreferences = mocker.mock<TodosLocalPreferences>()
        mocker.every { mockLocalPreferences.getTodoListOrderByOptions() } returns TodoOrderBy.entries
        mocker.every { mockLocalPreferences.getTodoListOrderBy() } returns TodoOrderBy.ADDED_AT_ASC
        mocker.every { mockLocalPreferences.getTodoListShowCompleted() } returns true

        val dateTimeUtils = mocker.mock<DateTimeUtils>()
        mocker.every { dateTimeUtils.currentTimestamp() } returns 0L

        val middleware = TodoListMiddleware(
            todosRepository = mockTodosRepository,
            todosLocalPreferences = mockLocalPreferences,
            dateTimeUtils = dateTimeUtils
        )

        val fetchAllAction = TodoListAction.FetchAll

        var updateLoadingActionReceived = false
        var updateLoadingActionIsLoading = false

        var updateListActionReceived = false
        var updateListActionTodos: List<TodoModel> = listOf()

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
        middleware.invoke(emptyAppState, fetchAllAction, dispatcher)

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

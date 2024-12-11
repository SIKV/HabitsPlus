package com.github.sikv.habitsplus.data.impl

import com.github.sikv.habitsplus.data.mapping.toLong
import com.github.sikv.habitsplus.data.model.TodoModel
import com.github.sikv.habitsplus.data.model.TodoOrderBy
import com.github.sikv.habitsplus.data.model.TodoStatus
import com.github.sikv.habitsplus.data.repository.impl.TodosRepositoryImpl
import com.github.sikv.habitsplus.data.source.TodosLocalDataSource
import com.github.sikv.habitsplus.util.MockDateTimeUtils
import com.github.sikv.habitsplus.util.testTodos
import org.kodein.mock.Mocker
import org.kodein.mock.UsesMocks
import org.kodein.mock.generated.mock
import kotlin.test.Test
import kotlin.test.assertEquals

@UsesMocks(TodosLocalDataSource::class)
class TodosRepositoryImplTest {

    @Test
    fun addTodo_updatesLocalDataSource() {
        // GIVEN
        val mocker = Mocker()

        val mockTodosLocalDataSource = mocker.mock<TodosLocalDataSource>()
        mocker.every {
            mockTodosLocalDataSource.insertTodo(isAny(), isAny(), isAny(), isAny(), isAny())
        } returns Unit

        val mockDateTimeUtils = MockDateTimeUtils()

        val repo = TodosRepositoryImpl(
            todosLocalDataSource = mockTodosLocalDataSource,
            dateTimeUtils = mockDateTimeUtils
        )

        val todo = TodoModel(
            status = TodoStatus.Todo,
            title = "Test todo",
            description = "Some description",
            dueDate = 90L
        )

        // WHEN
        repo.addTodo(todo)

        // THEN
        mocker.verify {
            mockTodosLocalDataSource.insertTodo(
                status = todo.status.toLong(),
                title = todo.title,
                description = todo.description,
                dueDateMs = todo.dueDate,
                addedAtMs = mockDateTimeUtils.testTimeMillis
            )
        }
    }

    @Test
    fun updateTodo_updatesLocalDataSource() {
        // GIVEN
        val mocker = Mocker()

        val mockTodosLocalDataSource = mocker.mock<TodosLocalDataSource>()
        mocker.every {
            mockTodosLocalDataSource.updateTodo(isAny(), isAny(), isAny(), isAny(), isAny(), isAny(), isAny())
        } returns true

        val mockDateTimeUtils = MockDateTimeUtils()

        val repo = TodosRepositoryImpl(
            todosLocalDataSource = mockTodosLocalDataSource,
            dateTimeUtils = mockDateTimeUtils
        )

        val todo = TodoModel(
            id = 100,
            status = TodoStatus.Done(doneAtMs = 1L),
            title = "Test update todo",
            description = "Update",
            dueDate = 40L
        )

        // WHEN
        repo.updateTodo(todo)

        // THEN
        mocker.verify {
            mockTodosLocalDataSource.updateTodo(
                id = todo.id,
                status = todo.status.toLong(),
                title = todo.title,
                description = todo.description,
                dueDateMs = todo.dueDate,
                doneAtMs = 1L,
                editedAtMs = mockDateTimeUtils.testTimeMillis
            )
        }
    }

    @Test
    fun getAllTodos_returnsSortedData() {
        // GIVEN
        val mocker = Mocker()

        val mockTodosLocalDataSource = mocker.mock<TodosLocalDataSource>()
        mocker.every { mockTodosLocalDataSource.selectAllTodos() } returns testTodos

        val mockDateTimeUtils = MockDateTimeUtils()

        val repo = TodosRepositoryImpl(
            todosLocalDataSource = mockTodosLocalDataSource,
            dateTimeUtils = mockDateTimeUtils
        )

        // WHEN
        val actualTodos = repo.getAllTodos(
            orderBy = TodoOrderBy.TITLE_DESC,
            showCompleted = false
        )

        // THEN
        val expectedTodos = testTodos
            .filter { it.status !is TodoStatus.Done }
            .sortedByDescending { it.title }

        assertEquals(expectedTodos, actualTodos)
    }

    @Test
    fun getAllTodos_returnsCompleted() {
        // GIVEN
        val mocker = Mocker()

        val mockTodosLocalDataSource = mocker.mock<TodosLocalDataSource>()
        mocker.every { mockTodosLocalDataSource.selectAllTodos() } returns testTodos

        val mockDateTimeUtils = MockDateTimeUtils()

        val repo = TodosRepositoryImpl(
            todosLocalDataSource = mockTodosLocalDataSource,
            dateTimeUtils = mockDateTimeUtils
        )

        // WHEN
        val actualTodos = repo.getAllTodos(
            orderBy = TodoOrderBy.TITLE_DESC,
            showCompleted = true
        )

        // THEN
        val expectedTodos = testTodos
            .sortedByDescending { it.title }

        assertEquals(expectedTodos, actualTodos)
    }
}

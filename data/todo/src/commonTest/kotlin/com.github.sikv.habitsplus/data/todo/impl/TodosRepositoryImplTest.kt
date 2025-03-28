package com.github.sikv.habitsplus.data.todo.impl

import com.github.sikv.habitsplus.data.todo.FakeDateTimeUtils
import com.github.sikv.habitsplus.data.todo.TodosDataSource
import com.github.sikv.habitsplus.data.todo.model.TodoModel
import com.github.sikv.habitsplus.data.todo.model.TodoOrderBy
import com.github.sikv.habitsplus.data.todo.model.TodoStatus
import com.github.sikv.habitsplus.data.todo.testTodos
import com.github.sikv.habitsplus.data.todo.toLong
import org.kodein.mock.Mocker
import org.kodein.mock.UsesMocks
import org.kodein.mock.generated.mock
import kotlin.test.Test
import kotlin.test.assertEquals

@UsesMocks(TodosDataSource::class)
class TodosRepositoryImplTest {

    @Test
    fun addTodo_updatesLocalDataSource() {
        // GIVEN
        val mocker = Mocker()

        val mockTodosLocalDataSource = mocker.mock<TodosDataSource>()
        mocker.every {
            mockTodosLocalDataSource.insertTodo(isAny(), isAny(), isAny(), isAny(), isAny())
        } returns Unit

        val mockDateTimeUtils = FakeDateTimeUtils()

        val repo = TodosRepositoryImpl(
            todosDataSource = mockTodosLocalDataSource,
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

        val mockTodosLocalDataSource = mocker.mock<TodosDataSource>()
        mocker.every {
            mockTodosLocalDataSource.updateTodo(isAny(), isAny(), isAny(), isAny(), isAny(), isAny(), isAny())
        } returns true

        val mockDateTimeUtils = FakeDateTimeUtils()

        val repo = TodosRepositoryImpl(
            todosDataSource = mockTodosLocalDataSource,
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

        val mockTodosLocalDataSource = mocker.mock<TodosDataSource>()
        mocker.every { mockTodosLocalDataSource.selectAllTodos() } returns testTodos

        val mockDateTimeUtils = FakeDateTimeUtils()

        val repo = TodosRepositoryImpl(
            todosDataSource = mockTodosLocalDataSource,
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

        val mockTodosLocalDataSource = mocker.mock<TodosDataSource>()
        mocker.every { mockTodosLocalDataSource.selectAllTodos() } returns testTodos

        val mockDateTimeUtils = FakeDateTimeUtils()

        val repo = TodosRepositoryImpl(
            todosDataSource = mockTodosLocalDataSource,
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

package com.github.sikv.habitsplus.data.impl

import com.github.sikv.habitsplus.data.mapping.toLong
import com.github.sikv.habitsplus.data.mock.MockTodosLocalDataSource
import com.github.sikv.habitsplus.data.model.Todo
import com.github.sikv.habitsplus.data.model.TodoOrderBy
import com.github.sikv.habitsplus.data.model.TodoStatus
import com.github.sikv.habitsplus.util.MockDateTimeUtils
import kotlin.test.Test
import kotlin.test.assertEquals

class TodosRepositoryImplTest {

    @Test
    fun addTodo_updatesLocalDataSource() {
        // GIVEN
        val mockTodosLocalDataSource = MockTodosLocalDataSource()
        val mockDateTimeUtils = MockDateTimeUtils()

        val repo = TodosRepositoryImpl(
            todosLocalDataSource = mockTodosLocalDataSource,
            dateTimeUtils = mockDateTimeUtils
        )

        val todo = Todo(
            status = TodoStatus.Todo,
            title = "Test todo",
            description = "Some description",
            dueDateMs = 90L
        )

        // WHEN
        repo.addTodo(todo)

        // THEN
        mockTodosLocalDataSource.verifyInsertTodoCalled(
            status = todo.status.toLong(),
            title = todo.title,
            description = todo.description,
            dueDateMs = todo.dueDateMs,
            addedAtMs = mockDateTimeUtils.testTimeMillis
        )
    }

    @Test
    fun updateTodo_updatesLocalDataSource() {
        // GIVEN
        val mockTodosLocalDataSource = MockTodosLocalDataSource()
        val mockDateTimeUtils = MockDateTimeUtils()

        val repo = TodosRepositoryImpl(
            todosLocalDataSource = mockTodosLocalDataSource,
            dateTimeUtils = mockDateTimeUtils
        )

        val todo = Todo(
            id = 100,
            status = TodoStatus.Done(doneAtMs = 1L),
            title = "Test update todo",
            description = "Update",
            dueDateMs = 40L
        )

        // WHEN
        repo.updateTodo(todo)

        // THEN
        mockTodosLocalDataSource.verifyUpdateTodoCalled(
            id = todo.id,
            status = todo.status.toLong(),
            title = todo.title,
            description = todo.description,
            dueDateMs = todo.dueDateMs,
            doneAtMs = 1L,
            editedAtMs = mockDateTimeUtils.testTimeMillis
        )
    }

    @Test
    fun getAllTodos_returnsSortedData() {
        // GIVEN
        val mockTodosLocalDataSource = MockTodosLocalDataSource()
        val mockDateTimeUtils = MockDateTimeUtils()

        val repo = TodosRepositoryImpl(
            todosLocalDataSource = mockTodosLocalDataSource,
            dateTimeUtils = mockDateTimeUtils
        )

        // WHEN
        val actualTodos = repo.getAllTodos(TodoOrderBy.TITLE_DESC)

        // THEN
        val expectedTodos = mockTodosLocalDataSource.testTodos.sortedByDescending { it.title }
        assertEquals(expectedTodos, actualTodos)
    }
}

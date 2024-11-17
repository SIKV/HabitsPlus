package com.github.sikv.habitsplus.data.impl

import com.github.sikv.habitsplus.data.mock.MockTodosLocalDataSource
import com.github.sikv.habitsplus.data.model.Todo
import com.github.sikv.habitsplus.data.model.TodoOrderBy
import kotlin.test.Test
import kotlin.test.assertEquals

class TodosRepositoryImplTest {

    @Test
    fun addTodo_updatesLocalDataSource() {
        // GIVEN
        val mockTodosLocalDataSource = MockTodosLocalDataSource()

        val repo = TodosRepositoryImpl(
            todosLocalDataSource = mockTodosLocalDataSource
        )

        val todo = Todo(
            title = "Test todo",
            description = "Some description",
            dueDateMs = 90L,
            addedAtMs = 10L,
            editedAtMs = null
        )

        // WHEN
        repo.addTodo(todo)

        // THEN
        mockTodosLocalDataSource.verifyInsertTodoCalled(
            title = todo.title,
            description = todo.description,
            dueDateMs = todo.dueDateMs,
            addedAtMs = todo.addedAtMs
        )
    }

    @Test
    fun getAllTodos_returnsSortedData() {
        // GIVEN
        val mockTodosLocalDataSource = MockTodosLocalDataSource()

        val repo = TodosRepositoryImpl(
            todosLocalDataSource = mockTodosLocalDataSource
        )

        // WHEN
        val actualTodos = repo.getAllTodos(TodoOrderBy.TITLE_DESC)

        // THEN
        val expectedTodos = mockTodosLocalDataSource.testTodos.sortedByDescending { it.title }
        assertEquals(expectedTodos, actualTodos)
    }
}

package com.github.sikv.habitsplus.data.mock

import com.github.sikv.habitsplus.data.local.TodosLocalDataSource
import com.github.sikv.habitsplus.data.model.Todo
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class MockTodosLocalDataSource : TodosLocalDataSource {

    val testTodos = listOf(
        Todo(
            id = 10,
            title = "Todo 10",
            description = null,
            dueDateTime = 123L,
            addedAt = 100L,
            editedAt = null
        ),
        Todo(
            id = 0,
            title = "0",
            description = "No description",
            dueDateTime = 1L,
            addedAt = 50L,
            editedAt = 100L
        ),
        Todo(
            id = 5,
            title = "Something",
            description = "Test",
            dueDateTime = 500L,
            addedAt = 1L,
            editedAt = null
        ),
    )

    private var isInsertTodoCalled: Boolean = false

    private var insertTodoTitle: String? = null
    private var insertTodoDescription: String? = null
    private var insertTodoDueDateTime: Long? = null
    private var insertTodoAddedAt: Long? = null

    private var isSelectAllTodosCalled: Boolean = false

    override fun insertTodo(title: String, description: String?, dueDateTime: Long?, addedAt: Long) {
        isInsertTodoCalled = true

        insertTodoTitle = title
        insertTodoDescription = description
        insertTodoDueDateTime = dueDateTime
        insertTodoAddedAt = addedAt
    }

    override fun selectAllTodos(): List<Todo> {
        isSelectAllTodosCalled = true
        return testTodos
    }

    fun verifyInsertTodoCalled(title: String, description: String?, dueDateTime: Long?, addedAt: Long) {
        assertTrue(isInsertTodoCalled)

        assertEquals(title, insertTodoTitle)
        assertEquals(description, insertTodoDescription)
        assertEquals(dueDateTime, insertTodoDueDateTime)
        assertEquals(addedAt, insertTodoAddedAt)
    }
}

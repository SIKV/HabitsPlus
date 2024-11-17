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
            dueDateMs = 123L,
            addedAtMs = 100L,
            editedAtMs = null
        ),
        Todo(
            id = 0,
            title = "0",
            description = "No description",
            dueDateMs = 1L,
            addedAtMs = 50L,
            editedAtMs = 100L
        ),
        Todo(
            id = 5,
            title = "Something",
            description = "Test",
            dueDateMs = 500L,
            addedAtMs = 1L,
            editedAtMs = null
        ),
    )

    private var isInsertTodoCalled: Boolean = false

    private var insertTodoTitle: String? = null
    private var insertTodoDescription: String? = null
    private var insertTodoDueDateMs: Long? = null
    private var insertTodoAddedAtMs: Long? = null

    private var isSelectAllTodosCalled: Boolean = false

    override fun insertTodo(title: String, description: String?, dueDateMs: Long?, addedAtMs: Long) {
        isInsertTodoCalled = true

        insertTodoTitle = title
        insertTodoDescription = description
        insertTodoDueDateMs = dueDateMs
        insertTodoAddedAtMs = addedAtMs
    }

    override fun selectAllTodos(): List<Todo> {
        isSelectAllTodosCalled = true
        return testTodos
    }

    fun verifyInsertTodoCalled(title: String, description: String?, dueDateMs: Long?, addedAtMs: Long) {
        assertTrue(isInsertTodoCalled)

        assertEquals(title, insertTodoTitle)
        assertEquals(description, insertTodoDescription)
        assertEquals(dueDateMs, insertTodoDueDateMs)
        assertEquals(addedAtMs, insertTodoAddedAtMs)
    }
}

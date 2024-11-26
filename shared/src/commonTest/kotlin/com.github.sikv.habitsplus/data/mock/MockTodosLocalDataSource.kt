package com.github.sikv.habitsplus.data.mock

import com.github.sikv.habitsplus.data.local.TodosLocalDataSource
import com.github.sikv.habitsplus.data.model.Todo
import com.github.sikv.habitsplus.data.model.TodoStatus
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class MockTodosLocalDataSource : TodosLocalDataSource {

    val testTodos = listOf(
        Todo(
            id = 10,
            status = TodoStatus.Todo,
            title = "Todo 10",
            description = null,
            dueDateMs = 123L,
            addedAtMs = 100L,
            editedAtMs = null
        ),
        Todo(
            id = 0,
            status = TodoStatus.Todo,
            title = "0",
            description = "No description",
            dueDateMs = 1L,
            addedAtMs = 50L,
            editedAtMs = 100L
        ),
        Todo(
            id = 5,
            status = TodoStatus.Todo,
            title = "Something",
            description = "Test",
            dueDateMs = 500L,
            addedAtMs = 1L,
            editedAtMs = null
        ),
    )

    private var isInsertTodoCalled: Boolean = false
    private var insertTodoStatus: Long? = null
    private var insertTodoTitle: String? = null
    private var insertTodoDescription: String? = null
    private var insertTodoDueDateMs: Long? = null
    private var insertTodoAddedAtMs: Long? = null

    private var isUpdateTodoCalled: Boolean = false
    private var updateTodoId: Long? = null
    private var updateTodoStatus: Long? = null
    private var updateTodoTitle: String? = null
    private var updateTodoDescription: String? = null
    private var updateTodoDueDateMs: Long? = null
    private var updateTodoDoneAtMs: Long? = null
    private var updateTodoEditedAtMs: Long? = null

    private var isSelectAllTodosCalled: Boolean = false

    override fun insertTodo(status: Long, title: String, description: String?, dueDateMs: Long?, addedAtMs: Long) {
        isInsertTodoCalled = true

        insertTodoStatus = status
        insertTodoTitle = title
        insertTodoDescription = description
        insertTodoDueDateMs = dueDateMs
        insertTodoAddedAtMs = addedAtMs
    }

    override fun updateTodo(
        id: Long,
        status: Long,
        title: String,
        description: String?,
        dueDateMs: Long?,
        doneAtMs: Long?,
        editedAtMs: Long?
    ): Boolean {
        isUpdateTodoCalled = true

        updateTodoId = id
        updateTodoStatus = status
        updateTodoTitle = title
        updateTodoDescription = description
        updateTodoDueDateMs = dueDateMs
        updateTodoDoneAtMs = doneAtMs
        updateTodoEditedAtMs = editedAtMs

        return true
    }

    override fun selectAllTodos(): List<Todo> {
        isSelectAllTodosCalled = true
        return testTodos
    }

    fun verifyInsertTodoCalled(status: Long, title: String, description: String?, dueDateMs: Long?, addedAtMs: Long) {
        assertTrue(isInsertTodoCalled)

        assertEquals(status, insertTodoStatus)
        assertEquals(title, insertTodoTitle)
        assertEquals(description, insertTodoDescription)
        assertEquals(dueDateMs, insertTodoDueDateMs)
        assertEquals(addedAtMs, insertTodoAddedAtMs)
    }

    fun verifyUpdateTodoCalled(
        id: Long,
        status: Long,
        title: String,
        description: String?,
        dueDateMs: Long?,
        doneAtMs: Long?,
        editedAtMs: Long?
    ) {
        assertTrue(isUpdateTodoCalled)

        assertEquals(id, updateTodoId)
        assertEquals(status, updateTodoStatus)
        assertEquals(title, updateTodoTitle)
        assertEquals(description, updateTodoDescription)
        assertEquals(dueDateMs, updateTodoDueDateMs)
        assertEquals(doneAtMs, updateTodoDoneAtMs)
        assertEquals(editedAtMs, updateTodoEditedAtMs)
    }
}

package com.github.sikv.habitsplus.data.mock

import com.github.sikv.habitsplus.data.TodosRepository
import com.github.sikv.habitsplus.data.model.Todo
import com.github.sikv.habitsplus.data.model.TodoOrderBy
import kotlin.test.assertTrue

internal class MockTodosRepository : TodosRepository {

    val testTodos = listOf(
        Todo(
            id = 1,
            title = "Todo 1",
            description = null,
            dueDateTime = 123L,
            addedAt = 100L,
            editedAt = null
        ),
        Todo(
            id = 2,
            title = "Todo 2",
            description = "Description 2",
            dueDateTime = 1L,
            addedAt = 50L,
            editedAt = 100L
        ),
    )

    private var isGetAllTodosCalled: Boolean = false

    override fun addTodo(todo: Todo) { }

    override fun getAllTodos(orderBy: TodoOrderBy): List<Todo> {
        isGetAllTodosCalled = true
        return testTodos
    }

    fun verifyGetAllTodosCalled() {
        assertTrue(isGetAllTodosCalled)
    }
}

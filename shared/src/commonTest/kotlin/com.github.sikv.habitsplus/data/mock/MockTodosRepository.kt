package com.github.sikv.habitsplus.data.mock

import com.github.sikv.habitsplus.data.TodosRepository
import com.github.sikv.habitsplus.data.model.Todo
import com.github.sikv.habitsplus.data.model.TodoOrderBy
import com.github.sikv.habitsplus.data.model.TodoStatus
import kotlin.test.assertTrue

internal class MockTodosRepository : TodosRepository {

    val testTodos = listOf(
        Todo(
            id = 1,
            status = TodoStatus.Todo,
            title = "Todo 1",
            description = null,
            dueDateMs = 123L,
            addedAtMs = 100L,
            editedAtMs = null
        ),
        Todo(
            id = 2,
            status = TodoStatus.Todo,
            title = "Todo 2",
            description = "Description 2",
            dueDateMs = 1L,
            addedAtMs = 50L,
            editedAtMs = 100L
        ),
    )

    private var isUpdateTodoCalled: Boolean = false
    private var isGetAllTodosCalled: Boolean = false

    override fun addTodo(todo: Todo) { }

    override fun updateTodo(todo: Todo): Boolean {
        isUpdateTodoCalled = true
        return true
    }

    override fun getAllTodos(orderBy: TodoOrderBy): List<Todo> {
        isGetAllTodosCalled = true
        return testTodos
    }

    fun verifyUpdateTodoCalled() {
        assertTrue(isUpdateTodoCalled)
    }

    fun verifyGetAllTodosCalled() {
        assertTrue(isGetAllTodosCalled)
    }
}

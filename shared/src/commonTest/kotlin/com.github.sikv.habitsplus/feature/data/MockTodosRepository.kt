package com.github.sikv.habitsplus.feature.data

import com.github.sikv.habitsplus.data.TodosRepository
import com.github.sikv.habitsplus.data.model.Todo
import kotlin.test.assertTrue

internal class MockTodosRepository : TodosRepository {

    val testTodos = listOf(
        Todo("1"),
        Todo("2"),
        Todo("3"),
    )

    private var isGetAllTodosCalled: Boolean = false

    override fun addTodo(todo: Todo) { }

    override fun getAllTodos(): List<Todo> {
        isGetAllTodosCalled = true
        return testTodos
    }

    fun verifyGetAllTodosCalled() {
        assertTrue(isGetAllTodosCalled)
    }
}

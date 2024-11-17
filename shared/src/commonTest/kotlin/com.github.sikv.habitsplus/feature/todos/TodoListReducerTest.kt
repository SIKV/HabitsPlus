package com.github.sikv.habitsplus.feature.todos

import com.github.sikv.habitsplus.data.model.Todo
import kotlin.test.Test
import kotlin.test.assertEquals

class TodoListReducerTest {

    @Test
    fun reducer_updatesState_whenUpdateLoadingAction() {
        val state = TodoListState.emptyState
        val action = TodoListAction.UpdateLoading(isLoading = true)

        val newState = todoListReducer(state, action)

        assertEquals(action.isLoading, newState.isLoading)
        assertEquals(state.todos, newState.todos)
    }

    @Test
    fun reducer_updatesState_whenUpdateListAction() {
        val todos = listOf(
            Todo(
                id = 100,
                title = "100",
                description = null,
                dueDateMs= 1L,
                addedAtMs = 1L,
                editedAtMs = null
            ),
            Todo(
                id = 200,
                title = "200",
                description = "Description",
                dueDateMs = 1L,
                addedAtMs = 5L,
                editedAtMs = 10L
            ),
        )

        val state = TodoListState.emptyState
        val action = TodoListAction.UpdateList(todos = todos)

        val newState = todoListReducer(state, action)

        assertEquals(false, newState.isLoading)
        assertEquals(todos, newState.todos)
    }
}

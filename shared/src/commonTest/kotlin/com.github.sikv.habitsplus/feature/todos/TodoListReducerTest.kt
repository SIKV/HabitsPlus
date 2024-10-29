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
            Todo("Todo 1"),
            Todo("Todo 2"),
            Todo("Todo 3"),
        )

        val state = TodoListState.emptyState
        val action = TodoListAction.UpdateList(todos = todos)

        val newState = todoListReducer(state, action)

        assertEquals(false, newState.isLoading)
        assertEquals(todos, newState.todos)
    }
}

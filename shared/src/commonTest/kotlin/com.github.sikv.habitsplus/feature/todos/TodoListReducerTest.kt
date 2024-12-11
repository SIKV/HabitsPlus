package com.github.sikv.habitsplus.feature.todos

import com.github.sikv.habitsplus.data.model.Metadata
import com.github.sikv.habitsplus.data.model.TodoModel
import com.github.sikv.habitsplus.data.model.TodoStatus
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
            TodoModel(
                id = 100,
                status = TodoStatus.Todo,
                title = "100",
                description = null,
                dueDate = 1L,
                metadata = Metadata(
                    addedAt = 1L,
                    editedAt = null
                )
            ),
            TodoModel(
                id = 200,
                status = TodoStatus.Todo,
                title = "200",
                description = "Description",
                dueDate = 1L,
                metadata = Metadata(
                    addedAt = 5L,
                    editedAt = 10L
                )
            ),
        )

        val state = TodoListState.emptyState
        val action = TodoListAction.UpdateList(todos = todos)

        val newState = todoListReducer(state, action)

        assertEquals(false, newState.isLoading)
        assertEquals(todos, newState.todos)
    }
}

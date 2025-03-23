package com.github.sikv.habitsplus.data.todo

import com.github.sikv.habitsplus.data.common.model.Metadata
import com.github.sikv.habitsplus.data.todo.model.TodoModel
import com.github.sikv.habitsplus.data.todo.model.TodoStatus

val testTodos = listOf(
    TodoModel(
        id = 1,
        status = TodoStatus.Todo,
        title = "Todo 1",
        description = null,
        dueDate = 123L,
        metadata = Metadata(
            addedAt = 100L,
            editedAt = null
        )
    ),
    TodoModel(
        id = 2,
        status = TodoStatus.Todo,
        title = "Todo 2",
        description = "Description 2",
        dueDate = 1L,
        metadata = Metadata(
            addedAt = 50L,
            editedAt = 100L
        )
    ),
    TodoModel(
        id = 3,
        status = TodoStatus.Done(0),
        title = "Todo 3",
        description = "Description 3",
        dueDate = 0L,
        metadata = Metadata(
            addedAt = 5L,
            editedAt = 1000L
        )
    ),
    TodoModel(
        id = 4,
        status = TodoStatus.Done(140),
        title = "Todo 4",
        description = null,
        dueDate = 50L,
        metadata = Metadata(
            addedAt = 50L,
            editedAt = 50L
        )
    ),
)

package com.github.sikv.habitsplus.util

import com.github.sikv.habitsplus.data.model.Todo
import com.github.sikv.habitsplus.data.model.TodoStatus

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
    Todo(
        id = 3,
        status = TodoStatus.Done(0),
        title = "Todo 3",
        description = "Description 3",
        dueDateMs = 0L,
        addedAtMs = 5L,
        editedAtMs = 1000L
    ),
    Todo(
        id = 4,
        status = TodoStatus.Done(140),
        title = "Todo 4",
        description = null,
        dueDateMs = 50L,
        addedAtMs = 50L,
        editedAtMs = 50L
    ),
)

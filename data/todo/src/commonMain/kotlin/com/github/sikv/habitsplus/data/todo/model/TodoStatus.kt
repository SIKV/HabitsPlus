package com.github.sikv.habitsplus.data.todo.model

sealed class TodoStatus {

    data object Todo : TodoStatus()

    data class Done(
        val doneAtMs: Long?, // in UTC milliseconds from the epoch.
    ) : TodoStatus()
}
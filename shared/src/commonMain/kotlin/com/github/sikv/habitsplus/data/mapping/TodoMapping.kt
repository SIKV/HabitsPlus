package com.github.sikv.habitsplus.data.mapping

import com.github.sikv.habitsplus.data.model.Todo
import com.github.sikv.habitsplus.data.model.TodoStatus

private const val STATUS_TODO = 1L
private const val STATUS_DONE = 2L

internal fun TodoStatus.toLong(): Long {
    return when (this) {
        TodoStatus.Todo -> STATUS_TODO
        is TodoStatus.Done -> STATUS_DONE
    }
}

internal fun TodoStatus.doneAtMsOrNull(): Long? {
    return when (this) {
        TodoStatus.Todo -> null
        is TodoStatus.Done -> doneAtMs
    }
}

internal fun mapTodoStatus(status: Long, doneAtMs: Long?): TodoStatus {
    return when (status) {
        STATUS_TODO -> TodoStatus.Todo
        STATUS_DONE -> TodoStatus.Done(doneAtMs)
        else -> TodoStatus.Todo
    }
}

internal fun mapTodo(
    id: Long,
    status: Long,
    title: String,
    description: String?,
    dueDateMs: Long?,
    doneAtMs: Long?,
    addedAtMs: Long,
    editedAtMs: Long?
): Todo {
    return Todo(
        id = id,
        status = mapTodoStatus(status, doneAtMs),
        title = title,
        description = description,
        dueDateMs = dueDateMs,
        addedAtMs = addedAtMs,
        editedAtMs = editedAtMs
    )
}

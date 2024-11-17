package com.github.sikv.habitsplus.data.mapping

import com.github.sikv.habitsplus.data.model.Todo

internal fun mapTodo(
    id: Long,
    title: String,
    description: String?,
    dueDateMs: Long?,
    addedAtMs: Long,
    editedAtMs: Long?
): Todo {
    return Todo(
        id = id,
        title = title,
        description = description,
        dueDateMs = dueDateMs,
        addedAtMs = addedAtMs,
        editedAtMs = editedAtMs
    )
}

package com.github.sikv.habitsplus.data.mapping

import com.github.sikv.habitsplus.data.model.Todo

internal fun mapTodo(
    id: Long,
    title: String,
    description: String?,
    dueDateTime: Long?,
    addedAt: Long,
    editedAt: Long?
): Todo {
    return Todo(
        id = id,
        title = title,
        description = description,
        dueDateTime = dueDateTime,
        addedAt = addedAt,
        editedAt = editedAt
    )
}

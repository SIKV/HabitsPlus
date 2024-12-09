package com.github.sikv.habitsplus.data.source

import com.github.sikv.habitsplus.data.model.Todo

internal interface TodosLocalDataSource {
    fun insertTodo(
        status: Long,
        title: String,
        description: String?,
        dueDateMs: Long?,
        addedAtMs: Long
    )

    fun updateTodo(
        id: Long,
        status: Long,
        title: String,
        description: String?,
        dueDateMs: Long?,
        doneAtMs: Long?,
        editedAtMs: Long?
    ) : Boolean

    fun selectAllTodos(): List<Todo>
}

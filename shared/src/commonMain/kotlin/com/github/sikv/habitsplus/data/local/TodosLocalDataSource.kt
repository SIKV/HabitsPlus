package com.github.sikv.habitsplus.data.local

import com.github.sikv.habitsplus.data.model.Todo

interface TodosLocalDataSource {
    fun insertTodo(
        title: String,
        description: String?,
        dueDateMs: Long?,
        addedAtMs: Long
    )
    fun selectAllTodos(): List<Todo>
}

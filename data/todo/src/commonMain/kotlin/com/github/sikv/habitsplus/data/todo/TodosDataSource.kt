package com.github.sikv.habitsplus.data.todo

import com.github.sikv.habitsplus.data.todo.model.TodoModel

interface TodosDataSource {
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

    fun selectAllTodos(): List<TodoModel>
}

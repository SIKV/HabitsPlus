package com.github.sikv.habitsplus.data.local.impl

import com.github.sikv.habitsplus.data.local.TodosLocalDataSource
import com.github.sikv.habitsplus.data.mapping.mapTodo
import com.github.sikv.habitsplus.data.model.Todo
import com.github.sikv.habitsplus.database.Database

class TodosLocalDataSourceImpl(
    private val database: Database
) : TodosLocalDataSource {

    override fun insertTodo(title: String, description: String?, dueDateMs: Long?, addedAtMs: Long) {
        database.dbQuery.insertTodo(
            title = title,
            description = description,
            due_date_ms = dueDateMs,
            added_at_ms = addedAtMs
        )
    }

    override fun selectAllTodos(): List<Todo> {
        return database.dbQuery
            .selectAllTodos(::mapTodo)
            .executeAsList()
    }
}

package com.github.sikv.habitsplus.data.source.impl

import com.github.sikv.habitsplus.data.mapping.mapTodo
import com.github.sikv.habitsplus.data.model.TodoModel
import com.github.sikv.habitsplus.data.source.TodosLocalDataSource
import com.github.sikv.habitsplus.database.TodosDatabaseManager

internal class TodosLocalDataSourceImpl(
    private val database: TodosDatabaseManager
) : TodosLocalDataSource {

    override fun insertTodo(status: Long, title: String, description: String?, dueDateMs: Long?, addedAtMs: Long) {
        database.dbQuery.insertTodo(
            status = status,
            title = title,
            description = description,
            due_date_ms = dueDateMs,
            added_at_ms = addedAtMs
        )
    }

    override fun updateTodo(
        id: Long,
        status: Long,
        title: String,
        description: String?,
        dueDateMs: Long?,
        doneAtMs: Long?,
        editedAtMs: Long?
    ): Boolean {
        try {
            database.dbQuery.updateTodo(
                status = status,
                title = title,
                description = description,
                due_date_ms = dueDateMs,
                done_at_ms = doneAtMs,
                edited_at_ms = editedAtMs,
                id = id
            )
            return true
        } catch (e: Exception) {
            // TODO: Print log.
            return false
        }
    }

    override fun selectAllTodos(): List<TodoModel> {
        return database.dbQuery
            .selectAllTodos(::mapTodo)
            .executeAsList()
    }
}

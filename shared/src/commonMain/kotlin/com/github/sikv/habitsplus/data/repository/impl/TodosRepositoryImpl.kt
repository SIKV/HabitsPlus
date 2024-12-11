package com.github.sikv.habitsplus.data.repository.impl

import com.github.sikv.habitsplus.data.mapping.doneAtMsOrNull
import com.github.sikv.habitsplus.data.mapping.toLong
import com.github.sikv.habitsplus.data.model.TodoModel
import com.github.sikv.habitsplus.data.model.TodoOrderBy
import com.github.sikv.habitsplus.data.model.TodoStatus
import com.github.sikv.habitsplus.data.repository.TodosRepository
import com.github.sikv.habitsplus.data.source.TodosLocalDataSource
import com.github.sikv.habitsplus.util.DateTimeUtils

internal class TodosRepositoryImpl(
    private val todosLocalDataSource: TodosLocalDataSource,
    private val dateTimeUtils: DateTimeUtils
) : TodosRepository {

    override fun addTodo(todo: TodoModel) {
        todosLocalDataSource.insertTodo(
            status = todo.status.toLong(),
            title = todo.title,
            description = todo.description,
            dueDateMs = todo.dueDate,
            addedAtMs = dateTimeUtils.currentTimeMillis()
        )
    }

    override fun updateTodo(todo: TodoModel): Boolean {
        return todosLocalDataSource.updateTodo(
            id = todo.id,
            status = todo.status.toLong(),
            title = todo.title,
            description = todo.description,
            dueDateMs = todo.dueDate,
            doneAtMs = todo.status.doneAtMsOrNull(),
            editedAtMs = dateTimeUtils.currentTimeMillis()
        )
    }

    override fun getAllTodos(orderBy: TodoOrderBy, showCompleted: Boolean): List<TodoModel> {
        return todosLocalDataSource.selectAllTodos()
            .filter { todo ->
                // Show all items if [showCompleted] is true.
                if (showCompleted) {
                    true
                } else {
                    // Otherwise show only not completed.
                    todo.status !is TodoStatus.Done
                }
            }
            .sortedWith { a, b ->
                when (orderBy) {
                    TodoOrderBy.TITLE_ASC -> a.title.compareTo(b.title)
                    TodoOrderBy.TITLE_DESC -> b.title.compareTo(a.title)
                    TodoOrderBy.DUE_DATE_ASC -> a.dueDate?.compareTo(b.dueDate ?: 0) ?: 0
                    TodoOrderBy.DUE_DATE_DESC -> b.dueDate?.compareTo(a.dueDate ?: 0) ?: 0
                    TodoOrderBy.ADDED_AT_ASC -> a.metadata.addedAt.compareTo(b.metadata.addedAt)
                    TodoOrderBy.ADDED_AT_DESC -> b.metadata.addedAt.compareTo(a.metadata.addedAt)
                }
            }
    }
}

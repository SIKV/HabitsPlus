package com.github.sikv.habitsplus.data.todo.impl

import com.github.sikv.habitsplus.data.common.DateTimeUtils
import com.github.sikv.habitsplus.data.todo.TodosDataSource
import com.github.sikv.habitsplus.data.todo.TodosRepository
import com.github.sikv.habitsplus.data.todo.doneAtMsOrNull
import com.github.sikv.habitsplus.data.todo.model.TodoModel
import com.github.sikv.habitsplus.data.todo.model.TodoOrderBy
import com.github.sikv.habitsplus.data.todo.model.TodoStatus
import com.github.sikv.habitsplus.data.todo.toLong

class TodosRepositoryImpl(
    private val todosDataSource: TodosDataSource,
    private val dateTimeUtils: DateTimeUtils
) : TodosRepository {

    override fun addTodo(todo: TodoModel) {
        todosDataSource.insertTodo(
            status = todo.status.toLong(),
            title = todo.title,
            description = todo.description,
            dueDateMs = todo.dueDate,
            addedAtMs = dateTimeUtils.currentTimestamp()
        )
    }

    override fun updateTodo(todo: TodoModel): Boolean {
        return todosDataSource.updateTodo(
            id = todo.id,
            status = todo.status.toLong(),
            title = todo.title,
            description = todo.description,
            dueDateMs = todo.dueDate,
            doneAtMs = todo.status.doneAtMsOrNull(),
            editedAtMs = dateTimeUtils.currentTimestamp()
        )
    }

    override fun getAllTodos(orderBy: TodoOrderBy, showCompleted: Boolean): List<TodoModel> {
        return todosDataSource.selectAllTodos()
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

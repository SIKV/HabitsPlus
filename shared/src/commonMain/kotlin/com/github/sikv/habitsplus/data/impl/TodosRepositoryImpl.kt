package com.github.sikv.habitsplus.data.impl

import com.github.sikv.habitsplus.data.TodosRepository
import com.github.sikv.habitsplus.data.local.TodosLocalDataSource
import com.github.sikv.habitsplus.data.model.Todo
import com.github.sikv.habitsplus.data.model.TodoOrderBy

class TodosRepositoryImpl(
    private val todosLocalDataSource: TodosLocalDataSource
) : TodosRepository {

    override fun addTodo(todo: Todo) {
        todosLocalDataSource.insertTodo(
            title = todo.title,
            description = todo.description,
            dueDateTime = todo.dueDateTime,
            addedAt = todo.addedAt
        )
    }

    override fun getAllTodos(orderBy: TodoOrderBy): List<Todo> {
        return todosLocalDataSource.selectAllTodos()
            .sortedWith { a, b ->
                when (orderBy) {
                    TodoOrderBy.TITLE_ASC -> a.title.compareTo(b.title)
                    TodoOrderBy.TITLE_DESC -> b.title.compareTo(a.title)
                    TodoOrderBy.DUE_DATE_ASC -> a.dueDateTime?.compareTo(b.dueDateTime ?: 0) ?: 0
                    TodoOrderBy.DUE_DATE_DESC -> b.dueDateTime?.compareTo(a.dueDateTime ?: 0) ?: 0
                    TodoOrderBy.ADDED_AT_ASC -> a.addedAt.compareTo(b.addedAt)
                    TodoOrderBy.ADDED_AT_DESC -> b.addedAt.compareTo(a.addedAt)
                }
            }
    }
}

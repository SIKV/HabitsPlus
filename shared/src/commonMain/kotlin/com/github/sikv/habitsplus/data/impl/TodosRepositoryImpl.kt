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
            dueDateMs = todo.dueDateMs,
            addedAtMs = todo.addedAtMs
        )
    }

    override fun getAllTodos(orderBy: TodoOrderBy): List<Todo> {
        return todosLocalDataSource.selectAllTodos()
            .sortedWith { a, b ->
                when (orderBy) {
                    TodoOrderBy.TITLE_ASC -> a.title.compareTo(b.title)
                    TodoOrderBy.TITLE_DESC -> b.title.compareTo(a.title)
                    TodoOrderBy.DUE_DATE_ASC -> a.dueDateMs?.compareTo(b.dueDateMs ?: 0) ?: 0
                    TodoOrderBy.DUE_DATE_DESC -> b.dueDateMs?.compareTo(a.dueDateMs ?: 0) ?: 0
                    TodoOrderBy.ADDED_AT_ASC -> a.addedAtMs.compareTo(b.addedAtMs)
                    TodoOrderBy.ADDED_AT_DESC -> b.addedAtMs.compareTo(a.addedAtMs)
                }
            }
    }
}

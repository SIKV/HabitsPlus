package com.github.sikv.habitsplus.data.todo

import com.github.sikv.habitsplus.data.todo.model.TodoOrderBy

interface TodosLocalPreferences {
    fun getTodoListOrderByOptions(): List<TodoOrderBy>
    fun getTodoListOrderBy(): TodoOrderBy
    fun setTodoListOrderBy(orderBy: TodoOrderBy)

    fun getTodoListShowCompleted(): Boolean
    fun setTodoListShowCompleted(showCompleted: Boolean)
}

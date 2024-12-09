package com.github.sikv.habitsplus.data.preferences

import com.github.sikv.habitsplus.data.model.TodoOrderBy

internal interface LocalPreferences {

    fun getTodoListOrderByOptions(): List<TodoOrderBy>
    fun getTodoListOrderBy(): TodoOrderBy
    fun setTodoListOrderBy(orderBy: TodoOrderBy)

    fun getTodoListShowCompleted(): Boolean
    fun setTodoListShowCompleted(showCompleted: Boolean)
}

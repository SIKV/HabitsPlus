package com.github.sikv.habitsplus.data.todo.impl

import com.github.sikv.habitsplus.data.todo.TodosLocalPreferences
import com.github.sikv.habitsplus.data.todo.model.TodoOrderBy

// TODO: Implement.
class TodosLocalPreferencesImpl : TodosLocalPreferences {

    private var todoListOrderBy: TodoOrderBy = TodoOrderBy.ADDED_AT_DESC
    private var todoListShowCompleted: Boolean = false

    override fun getTodoListOrderByOptions(): List<TodoOrderBy> {
        return TodoOrderBy.entries
    }

    override fun getTodoListOrderBy(): TodoOrderBy {
        return todoListOrderBy
    }

    override fun setTodoListOrderBy(orderBy: TodoOrderBy) {
        todoListOrderBy = orderBy
    }

    override fun getTodoListShowCompleted(): Boolean {
        return todoListShowCompleted
    }

    override fun setTodoListShowCompleted(showCompleted: Boolean) {
        todoListShowCompleted = showCompleted
    }
}

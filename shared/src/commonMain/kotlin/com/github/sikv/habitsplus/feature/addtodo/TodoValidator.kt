package com.github.sikv.habitsplus.feature.addtodo

import com.github.sikv.habitsplus.data.model.TodoModel

internal object TodoValidator {

    fun checkErrors(todo: TodoModel): AddTodoError? {
        return if (todo.title.isBlank()) {
            AddTodoError.EmptyTitle
        } else {
            null
        }
    }
}

package com.github.sikv.habitsplus.feature.addtodo

import com.github.sikv.habitsplus.data.model.Todo

internal object TodoValidator {

    fun checkErrors(todo: Todo): AddTodoError? {
        return if (todo.title.isBlank()) {
            AddTodoError.EmptyTitle
        } else {
            null
        }
    }
}

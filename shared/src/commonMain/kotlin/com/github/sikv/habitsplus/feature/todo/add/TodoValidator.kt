package com.github.sikv.habitsplus.feature.todo.add

import com.github.sikv.habitsplus.data.todo.model.TodoModel
import com.github.sikv.habitsplus.util.ModelValidator

internal class TodoValidator : ModelValidator<TodoModel, AddTodoError?> {

    override fun checkErrors(model: TodoModel): AddTodoError? {
        return if (model.title.isBlank()) {
            AddTodoError.EmptyTitle
        } else {
            null
        }
    }
}

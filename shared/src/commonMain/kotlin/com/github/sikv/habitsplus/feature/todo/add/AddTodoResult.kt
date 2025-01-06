package com.github.sikv.habitsplus.feature.todo.add

sealed class AddTodoResult {

    data object Success : AddTodoResult()

    data class Failure(
        val error: AddTodoError
    ) : AddTodoResult()
}

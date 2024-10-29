package com.github.sikv.habitsplus.feature.addtodo

sealed class AddTodoResult {

    data object Success : AddTodoResult()

    data class Failure(
        val error: AddTodoError
    ) : AddTodoResult()
}

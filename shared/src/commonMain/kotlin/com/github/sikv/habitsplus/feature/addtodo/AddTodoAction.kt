package com.github.sikv.habitsplus.feature.addtodo

import com.github.sikv.habitsplus.store.Action

sealed class AddTodoAction : Action {

    data class UpdateTitle(
        val title: String
    ) : AddTodoAction()

    data object Add : AddTodoAction()
}

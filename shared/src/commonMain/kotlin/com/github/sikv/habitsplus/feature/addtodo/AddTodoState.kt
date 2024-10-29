package com.github.sikv.habitsplus.feature.addtodo

import com.github.sikv.habitsplus.store.StoreState

data class AddTodoState(
    val title: String
) : StoreState {

    companion object {
        val emptyState = AddTodoState(
            title = ""
        )
    }
}

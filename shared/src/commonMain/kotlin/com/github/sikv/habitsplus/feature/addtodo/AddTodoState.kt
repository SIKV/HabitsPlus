package com.github.sikv.habitsplus.feature.addtodo

import com.github.sikv.habitsplus.data.model.Timestamp
import com.github.sikv.habitsplus.store.StoreState

data class AddTodoState(
    val title: String,
    val description: String?,
    val dueDate: Timestamp?,
    val dueTimeHour: Int?, // 24-hour clock.
    val dueTimeMinute: Int?,
) : StoreState {

    companion object {
        val emptyState = AddTodoState(
            title = "",
            description = null,
            dueDate = null,
            dueTimeHour = null,
            dueTimeMinute = null
        )
    }
}

package com.github.sikv.habitsplus.feature.addtodo

import com.github.sikv.habitsplus.store.StoreState

data class AddTodoState(
    val title: String,
    val description: String?,
    val dueDateMs: Long?, // in UTC milliseconds from the epoch.
    val dueTimeHour: Int?, // 24-hour clock.
    val dueTimeMinute: Int?,
) : StoreState {

    companion object {
        val emptyState = AddTodoState(
            title = "",
            description = null,
            dueDateMs = null,
            dueTimeHour = null,
            dueTimeMinute = null
        )
    }
}

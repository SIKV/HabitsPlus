package com.github.sikv.habitsplus.feature.todo.add

import com.github.sikv.habitsplus.data.model.Timestamp
import com.github.sikv.habitsplus.store.Action

sealed class AddTodoAction : Action {

    data class UpdateTitle(
        val title: String
    ) : AddTodoAction()

    data class UpdateDescription(
        val description: String?
    ) : AddTodoAction()

    data class UpdateDueDate(
        val dueDate: Timestamp?
    ) : AddTodoAction()

    data class UpdateDueTime(
        val dueTimeHour: Int?, // 24-hour clock.
        val dueTimeMinute: Int?
    ) : AddTodoAction()

    data class Update(
        val title: String,
        val description: String?,
        val dueDate: Timestamp?,
        val dueTimeHour: Int?,
        val dueTimeMinute: Int?
    ) : AddTodoAction()

    data object Save : AddTodoAction()
}

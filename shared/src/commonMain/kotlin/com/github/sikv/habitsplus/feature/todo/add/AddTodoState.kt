package com.github.sikv.habitsplus.feature.todo.add

import com.github.sikv.habitsplus.data.model.Timestamp
import com.github.sikv.habitsplus.store.StoreState

data class AddTodoState(
    val title: String = "",
    val description: String? = null,
    val dueDate: Timestamp? = null,
    val dueTimeHour: Int? = null, // 24-hour clock.
    val dueTimeMinute: Int? = null
) : StoreState

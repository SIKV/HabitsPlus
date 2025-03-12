package com.github.sikv.habitsplus.feature.todo.add

import com.github.sikv.habitsplus.DefaultConfig
import com.github.sikv.habitsplus.data.model.Timestamp
import com.github.sikv.habitsplus.store.StoreState

data class AddTodoState(
    val title: String = "",
    val titleMaxLength: Int = DefaultConfig.ADD_TODO_TITLE_MAX_LENGTH,
    val description: String? = null,
    val descriptionMaxLength: Int = DefaultConfig.ADD_TODO_DESCRIPTION_MAX_LENGTH,
    val descriptionMinLines: Int = DefaultConfig.ADD_TODO_DESCRIPTION_MIN_LINES,
    val dueDate: Timestamp? = null,
    val dueTimeHour: Int? = null, // 24-hour clock.
    val dueTimeMinute: Int? = null
) : StoreState

package com.github.sikv.habitsplus.feature.todo.add

import com.github.sikv.habitsplus.data.common.model.Timestamp
import com.github.sikv.habitsplus.store.StoreState

private const val TITLE_MAX_LENGTH = 64
private const val DESCRIPTION_MAX_LENGTH = 250
private const val DESCRIPTION_MIN_LINES = 3

data class AddTodoState(
    val title: String = "",
    val titleMaxLength: Int = TITLE_MAX_LENGTH,
    val description: String? = null,
    val descriptionMaxLength: Int = DESCRIPTION_MAX_LENGTH,
    val descriptionMinLines: Int = DESCRIPTION_MIN_LINES,
    val dueDate: Timestamp? = null,
    val dueTimeHour: Int? = null, // 24-hour clock.
    val dueTimeMinute: Int? = null
) : StoreState

package com.github.sikv.habitsplus.data.todo.model

import com.github.sikv.habitsplus.data.common.AUTOINCREMENT_ID
import com.github.sikv.habitsplus.data.common.model.Metadata
import com.github.sikv.habitsplus.data.common.model.Timestamp

data class TodoModel(
    val id: Long = AUTOINCREMENT_ID,
    val status: TodoStatus,
    val title: String,
    val description: String?,
    val dueDate: Timestamp?,
    val metadata: Metadata = Metadata()
)
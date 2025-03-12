package com.github.sikv.habitsplus.data.model

data class TodoModel(
    val id: Long = AUTOINCREMENT_ID,
    val status: TodoStatus,
    val title: String,
    val description: String?,
    val dueDate: Timestamp?,
    val metadata: Metadata = Metadata()
)

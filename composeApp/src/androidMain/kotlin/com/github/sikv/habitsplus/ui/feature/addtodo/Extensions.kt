package com.github.sikv.habitsplus.ui.feature.addtodo

import android.content.Context
import com.github.sikv.habitsplus.R
import com.github.sikv.habitsplus.feature.todo.add.AddTodoError

fun AddTodoError.localizedMessage(context: Context): String {
    return when (this) {
        AddTodoError.EmptyTitle -> context.getString(R.string.add_todo_error_empty_title)
    }
}

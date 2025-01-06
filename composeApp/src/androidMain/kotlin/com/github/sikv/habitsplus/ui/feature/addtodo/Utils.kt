package com.github.sikv.habitsplus.ui.feature.addtodo

import com.github.sikv.habitsplus.feature.todo.add.AddTodoState
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

// TODO: This is a temporary implementation.
fun convertStateToDate(state: AddTodoState): String {
    val dueDateMs = state.dueDate
    return if (dueDateMs != null) {
        val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
        return formatter.format(Date(dueDateMs))
    } else {
        ""
    }
}

// TODO: This is a temporary implementation.
fun convertStateToTime(state: AddTodoState): String {
    val dueTimeHour = state.dueTimeHour
    val dueTimeMinute = state.dueTimeMinute

    return if (dueTimeHour != null && dueTimeMinute != null) {
        val formatter = SimpleDateFormat("hh:mm a", Locale.getDefault())

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, dueTimeHour)
        calendar.set(Calendar.MINUTE, dueTimeMinute)

        return formatter.format(Date(calendar.timeInMillis))
    } else {
        ""
    }
}

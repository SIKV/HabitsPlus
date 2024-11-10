package com.github.sikv.habitsplus.ui.feature.addtodo

import com.github.sikv.habitsplus.feature.addtodo.AddTodoState
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

// TODO: This is a temporary implementation.
fun convertStateToDate(state: AddTodoState): String {
    val dueDate = state.dueDate
    return if (dueDate != null) {
        val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
        return formatter.format(Date(dueDate))
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

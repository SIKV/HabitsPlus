package com.github.sikv.habitsplus.feature.addtodo

import com.github.sikv.habitsplus.store.Reducer

val addTodoReducer: Reducer<AddTodoState, AddTodoAction> = { state, action ->
    when (action) {
        is AddTodoAction.UpdateTitle -> state.copy(
            title = action.title
        )
        is AddTodoAction.UpdateDescription -> state.copy(
            description = action.description
        )
        is AddTodoAction.UpdateDueDate -> state.copy(
            dueDateMs = action.dueDateMs
        )
        is AddTodoAction.UpdateDueTime -> state.copy(
            dueTimeHour = action.dueTimeHour,
            dueTimeMinute = action.dueTimeMinute
        )
        is AddTodoAction.Update -> state.copy(
            title = action.title,
            description = action.description,
            dueDateMs = action.dueDateMs,
            dueTimeHour = action.dueTimeHour,
            dueTimeMinute = action.dueTimeMinute
        )
        else -> state
    }
}

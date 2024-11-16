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
            dueDate = action.dueDate
        )
        is AddTodoAction.UpdateDueTime -> state.copy(
            dueTimeHour = action.dueTimeHour, // TODO: Check dueTimeIs24hour field.
            dueTimeMinute = action.dueTimeMinute
        )
        is AddTodoAction.Update -> state.copy(
            title = action.title,
            description = action.description,
            dueDate = action.dueDate,
            dueTimeHour = action.dueTimeHour, // TODO: Check dueTimeIs24hour field.
            dueTimeMinute = action.dueTimeMinute
        )
        else -> state
    }
}

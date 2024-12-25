package com.github.sikv.habitsplus.store

import com.github.sikv.habitsplus.feature.activity.add.AddActivityAction
import com.github.sikv.habitsplus.feature.activity.add.addActivityReducer
import com.github.sikv.habitsplus.feature.addtodo.AddTodoAction
import com.github.sikv.habitsplus.feature.addtodo.addTodoReducer
import com.github.sikv.habitsplus.feature.todos.TodoListAction
import com.github.sikv.habitsplus.feature.todos.todoListReducer

val appReducer: Reducer<AppState, Action> = { state, action ->
    when (action) {
        is TodoListAction -> state.copy(
            todoListState = todoListReducer(state.todoListState, action)
        )
        is AddTodoAction -> state.copy(
            addTodoState = addTodoReducer(state.addTodoState, action)
        )
        is AddActivityAction -> state.copy(
            addActivityState = addActivityReducer(state.addActivityState, action)
        )
        else -> state
    }
}

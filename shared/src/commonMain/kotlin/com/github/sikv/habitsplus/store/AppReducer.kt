package com.github.sikv.habitsplus.store

import com.github.sikv.habitsplus.feature.activity.add.AddActivityAction
import com.github.sikv.habitsplus.feature.activity.add.addActivityReducer
import com.github.sikv.habitsplus.feature.activity.list.ActivityListAction
import com.github.sikv.habitsplus.feature.activity.list.activityListReducer
import com.github.sikv.habitsplus.feature.label.add.AddLabelAction
import com.github.sikv.habitsplus.feature.label.add.addLabelReducer
import com.github.sikv.habitsplus.feature.todo.add.AddTodoAction
import com.github.sikv.habitsplus.feature.todo.add.addTodoReducer
import com.github.sikv.habitsplus.feature.todo.list.TodoListAction
import com.github.sikv.habitsplus.feature.todo.list.todoListReducer

val appReducer: Reducer<AppState, Action> = { state, action ->
    when (action) {
        is TodoListAction -> state.copy(
            todoListState = todoListReducer(state.todoListState, action)
        )
        is AddTodoAction -> state.copy(
            addTodoState = addTodoReducer(state.addTodoState, action)
        )
        is ActivityListAction -> state.copy(
            activityListState = activityListReducer(state.activityListState, action)
        )
        is AddActivityAction -> state.copy(
            addActivityState = addActivityReducer(state.addActivityState, action)
        )
        is AddLabelAction -> state.copy(
            addLabelState = addLabelReducer(state.addLabelState, action)
        )
        else -> state
    }
}

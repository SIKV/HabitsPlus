package com.github.sikv.habitsplus.store

import com.github.sikv.habitsplus.feature.activity.add.AddActivityState
import com.github.sikv.habitsplus.feature.activity.list.ActivityListState
import com.github.sikv.habitsplus.feature.label.add.AddLabelState
import com.github.sikv.habitsplus.feature.label.list.LabelListState
import com.github.sikv.habitsplus.feature.todo.add.AddTodoState
import com.github.sikv.habitsplus.feature.todo.list.TodoListState

data class AppState(
    val todoListState: TodoListState = TodoListState(),
    val addTodoState: AddTodoState = AddTodoState(),
    val activityListState: ActivityListState = ActivityListState(),
    val addActivityState: AddActivityState = AddActivityState(),
    val labelListState: LabelListState = LabelListState(),
    val addLabelState: AddLabelState = AddLabelState()
): StoreState {

    companion object {
        val emptyState = AppState()
    }
}

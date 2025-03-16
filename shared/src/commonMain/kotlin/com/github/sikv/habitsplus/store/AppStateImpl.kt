package com.github.sikv.habitsplus.store

import com.github.sikv.habitsplus.feature.activity.add.AddActivityState
import com.github.sikv.habitsplus.feature.activity.list.ActivityListState
import com.github.sikv.habitsplus.feature.label.add.AddLabelState
import com.github.sikv.habitsplus.feature.label.list.LabelListState
import com.github.sikv.habitsplus.feature.todo.add.AddTodoState
import com.github.sikv.habitsplus.feature.todo.list.TodoListState

data class AppStateImpl(
    val todoListState: TodoListState = TodoListState(),
    val addTodoState: AddTodoState = AddTodoState(),
    val activityListState: ActivityListState = ActivityListState(),
    val addActivityState: AddActivityState = AddActivityState(),
    val labelListState: LabelListState = LabelListState(),
    val addLabelState: AddLabelState = AddLabelState()
): AppState {

    companion object {
        val emptyState = AppStateImpl()
    }

    override fun todoListState(): StoreState = todoListState
    override fun addTodoState(): StoreState = addTodoState

    override fun activityListState(): StoreState = activityListState
    override fun addActivityState(): StoreState = addActivityState

    override fun labelListState(): StoreState = labelListState
    override fun addLabelState(): StoreState = addLabelState
}

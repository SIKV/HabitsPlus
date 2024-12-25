package com.github.sikv.habitsplus.store

import com.github.sikv.habitsplus.feature.activity.add.AddActivityState
import com.github.sikv.habitsplus.feature.addtodo.AddTodoState
import com.github.sikv.habitsplus.feature.todos.TodoListState

data class AppState(
    val todoListState: TodoListState = TodoListState(),
    val addTodoState: AddTodoState = AddTodoState(),
    val addActivityState: AddActivityState = AddActivityState()
): StoreState

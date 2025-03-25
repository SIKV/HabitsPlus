package com.github.sikv.habitsplus.feature.todo.list

import com.github.sikv.habitsplus.store.AppState
import com.github.sikv.habitsplus.store.StoreState

val emptyAppState = object : AppState {

    override fun todoListState(): StoreState = TodoListState()
    override fun addTodoState(): StoreState = throw NotImplementedError()

    override fun activityListState(): StoreState = throw NotImplementedError()
    override fun addActivityState(): StoreState = throw NotImplementedError()

    override fun labelListState(): StoreState = throw NotImplementedError()

    override fun addLabelState(): StoreState = throw NotImplementedError()
}

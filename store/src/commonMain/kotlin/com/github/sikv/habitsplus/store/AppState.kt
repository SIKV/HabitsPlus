package com.github.sikv.habitsplus.store

interface AppState : StoreState {
    fun todoListState(): StoreState
    fun addTodoState(): StoreState

    fun activityListState(): StoreState
    fun addActivityState(): StoreState

    fun labelListState(): StoreState
    fun addLabelState(): StoreState
}

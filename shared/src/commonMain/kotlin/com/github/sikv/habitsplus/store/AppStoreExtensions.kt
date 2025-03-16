package com.github.sikv.habitsplus.store

import com.github.sikv.habitsplus.feature.activity.add.AddActivityState
import com.github.sikv.habitsplus.feature.activity.list.ActivityListState
import com.github.sikv.habitsplus.feature.label.add.AddLabelState
import com.github.sikv.habitsplus.feature.label.list.LabelListState
import com.github.sikv.habitsplus.feature.todo.add.AddTodoState
import com.github.sikv.habitsplus.feature.todo.list.TodoListState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest

@OptIn(ExperimentalCoroutinesApi::class)
inline fun<reified S : StoreState> Store<AppState>.observeFeatureState(): Flow<S> {
    return observeState().mapLatest {
        val state = when (S::class) {
            TodoListState::class -> it.todoListState()
            AddTodoState::class -> it.addTodoState()

            ActivityListState::class -> it.activityListState()
            AddActivityState::class -> it.addActivityState()

            LabelListState::class -> it.labelListState()
            AddLabelState::class ->it.addLabelState()

            else -> throw Exception("State not found.")
        }
        state as S
    }
}

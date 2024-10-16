package com.github.sikv.habitsplus.ui.feature.todos

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.sikv.habitsplus.feature.todos.TodoListAction
import com.github.sikv.habitsplus.feature.todos.TodoListState
import com.github.sikv.habitsplus.store.AppStore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.mapLatest
import org.koin.compose.koinInject

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun TodosScreen(store: AppStore = koinInject()) {
    val state by store.observeState()
        .mapLatest { it.todoListState }
        .collectAsStateWithLifecycle(TodoListState.emptyState)

    // Note: not recommended approach.
    LaunchedEffect(Unit) {
        store.dispatch(TodoListAction.FetchAll)
    }

    if (state.isLoading) {
        CircularProgressIndicator()
    } else {
        LazyColumn {
            items(state.todos) { todo ->
                Text(todo.title)
            }
        }
    }
}

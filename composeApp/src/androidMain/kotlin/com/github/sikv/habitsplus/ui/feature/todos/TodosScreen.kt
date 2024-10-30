package com.github.sikv.habitsplus.ui.feature.todos

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.sikv.habitsplus.R
import com.github.sikv.habitsplus.feature.todos.TodoListAction
import com.github.sikv.habitsplus.feature.todos.TodoListState
import com.github.sikv.habitsplus.store.AppStore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.mapLatest
import org.koin.compose.koinInject

@OptIn(ExperimentalCoroutinesApi::class, ExperimentalMaterial3Api::class)
@Composable
fun TodosScreen(store: AppStore = koinInject()) {
    val state by store.observeState()
        .mapLatest { it.todoListState }
        .collectAsStateWithLifecycle(TodoListState.emptyState)

    // Note: not recommended approach.
    LaunchedEffect(Unit) {
        store.dispatch(TodoListAction.FetchAll)
    }

    Scaffold(
        topBar = {
            MediumTopAppBar(
                title = { Text(stringResource(R.string.todos_screen_title)) },
            )
        }
    ) { innerPadding ->
        if (state.isLoading) {
            CircularProgressIndicator()
        } else {
            LazyColumn(modifier = Modifier.padding(innerPadding)) {
                items(state.todos) { todo ->
                    Text(todo.title)
                }
            }
        }
    }
}

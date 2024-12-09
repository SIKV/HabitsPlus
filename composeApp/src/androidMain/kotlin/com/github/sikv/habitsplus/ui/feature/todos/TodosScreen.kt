package com.github.sikv.habitsplus.ui.feature.todos

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.sikv.habitsplus.R
import com.github.sikv.habitsplus.data.model.Todo
import com.github.sikv.habitsplus.feature.todos.TodoListAction
import com.github.sikv.habitsplus.feature.todos.TodoListState
import com.github.sikv.habitsplus.store.AppStore
import com.github.sikv.habitsplus.ui.components.TopAppBarMenuButton
import com.github.sikv.habitsplus.ui.components.TopAppBarSortButton
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.mapLatest
import org.koin.compose.koinInject

@OptIn(ExperimentalCoroutinesApi::class, ExperimentalMaterial3Api::class)
@Composable
fun TodosScreen(
    onNavigateToAddTodo: () -> Unit,
    store: AppStore = koinInject()
) {
    val state by store.observeState()
        .mapLatest { it.todoListState }
        .collectAsStateWithLifecycle(TodoListState.emptyState)

    // Note: not recommended approach.
    LaunchedEffect(Unit) {
        store.dispatch(TodoListAction.FetchAll)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.todos_screen_title)) },
                actions = {
                    TopAppBarSortButton(
                        onClick = {
                            // TODO: Implement.
                        }
                    )
                    TopAppBarMenuButton(
                        onClick = {
                            // TODO: Implement.
                        }
                    )
                }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = onNavigateToAddTodo,
                icon = { Icon(Icons.Default.Add, stringResource(R.string.todos_screen_add_button_content_desc)) },
                text = { Text(stringResource(R.string.todos_screen_add_button)) }
            )
        }
    ) { innerPadding ->
        if (state.isLoading) {
            CircularProgressIndicator()
        } else {
            LazyColumn(modifier = Modifier.padding(innerPadding)) {
                items(state.todos) { todo ->
                    TodoItem(todo)
                    HorizontalDivider()
                }
            }
        }
    }
}

@Composable
private fun TodoItem(todo: Todo) {
    ListItem(
        headlineContent = {
            Text(todo.title)
        },
        overlineContent = {
            Text("Due to: 6 Nov 2025")
        },
        supportingContent = {
            Text("Very long todo item description that will probably take multiple lines of text.",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis)
        },
        trailingContent = {
            Checkbox(
                checked = false,
                onCheckedChange = {
                    // TODO: Implement.
                }
            )
        },
        modifier = Modifier
            .clickable {
                // TODO: Implement.
            }
    )
}

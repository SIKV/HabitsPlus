package com.github.sikv.habitsplus.ui.feature.addtodo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.sikv.habitsplus.R
import com.github.sikv.habitsplus.feature.addtodo.AddTodoAction
import com.github.sikv.habitsplus.feature.addtodo.AddTodoHandleResultEffect
import com.github.sikv.habitsplus.feature.addtodo.AddTodoResult
import com.github.sikv.habitsplus.feature.addtodo.AddTodoState
import com.github.sikv.habitsplus.store.AppStore
import com.github.sikv.habitsplus.ui.components.TopAppBar
import com.github.sikv.habitsplus.ui.theme.spacing
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.mapLatest
import org.koin.compose.koinInject

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun AddTodoScreen(
    onNavigateBack: () -> Unit,
    store: AppStore = koinInject()
) {
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }

    val state by store.observeState()
        .mapLatest { it.addTodoState }
        .collectAsStateWithLifecycle(AddTodoState.emptyState)

    val addTodoResultEffect by store.observeSideEffect()
        .filterIsInstance<AddTodoHandleResultEffect>()
        .collectAsStateWithLifecycle(null)

    LaunchedEffect(addTodoResultEffect) {
        addTodoResultEffect?.result?.let { result ->
            when (result) {
                AddTodoResult.Success -> onNavigateBack()
                is AddTodoResult.Failure -> {
                    snackbarHostState.showSnackbar(result.error.localizedMessage(context))
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                onNavigateBack = onNavigateBack,
                title = stringResource(R.string.add_todo_screen_title)
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(MaterialTheme.spacing.medium)
        ) {
            OutlinedTextField(
                value = state.title,
                onValueChange = { value ->
                    store.dispatch(AddTodoAction.UpdateTitle(value))
                },
                label = { Text(stringResource(R.string.add_todo_title_label)) },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    store.dispatch(AddTodoAction.Add)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.add_todo_save_button))
            }
        }
    }
}

package com.github.sikv.habitsplus.ui.feature.addtodo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.sikv.habitsplus.DefaultConfig
import com.github.sikv.habitsplus.R
import com.github.sikv.habitsplus.feature.addtodo.AddTodoAction
import com.github.sikv.habitsplus.feature.addtodo.AddTodoError
import com.github.sikv.habitsplus.feature.addtodo.AddTodoHandleResultEffect
import com.github.sikv.habitsplus.feature.addtodo.AddTodoResult
import com.github.sikv.habitsplus.feature.addtodo.AddTodoState
import com.github.sikv.habitsplus.store.AppStore
import com.github.sikv.habitsplus.ui.components.Padding
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

    val formattedDate = remember(state) { convertStateToDate(state) }
    val formattedTime = remember(state) { convertStateToTime(state) }

    var titleHasError by remember(state) { mutableStateOf(false) }

    LaunchedEffect(addTodoResultEffect) {
        addTodoResultEffect?.result?.let { result ->
            when (result) {
                AddTodoResult.Success -> onNavigateBack()
                is AddTodoResult.Failure -> {
                    when (result.error) {
                        AddTodoError.EmptyTitle -> titleHasError = true
                    }
                    snackbarHostState.showSnackbar(result.error.localizedMessage(context))
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                onNavigateBack = onNavigateBack,
                title = stringResource(R.string.add_todo_screen_title),
                actions = {
                    Button(
                        onClick = {
                            store.dispatch(AddTodoAction.Save)
                        },
                        modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium)
                    ) {
                        Text(stringResource(R.string.add_todo_save_button))
                    }
                }
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
            // TODO: Show length counter.
            OutlinedTextField(
                value = state.title,
                onValueChange = { value ->
                    store.dispatch(AddTodoAction.UpdateTitle(
                        value.take(DefaultConfig.addTodoTitleMaxLength)
                    ))
                },
                label = { Text(stringResource(R.string.add_todo_title_label)) },
                supportingText = {
                    Text(
                        stringResource(R.string.label_required_field),
                    )
                },
                maxLines = DefaultConfig.addTodoTitleMaxLines,
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    capitalization = KeyboardCapitalization.Sentences
                ),
                isError = titleHasError,
                modifier = Modifier.fillMaxWidth()
            )

            Padding(vertical = MaterialTheme.spacing.small)

            // TODO: Show length counter.
            OutlinedTextField(
                value = state.description ?: "",
                onValueChange = { value ->
                    store.dispatch(AddTodoAction.UpdateDescription(
                        value.take(DefaultConfig.addTodoDescriptionMaxLength)
                    ))
                },
                label = { Text(stringResource(R.string.add_todo_description_label)) },
                minLines = DefaultConfig.addTodoDescriptionMaxLines,
                maxLines = DefaultConfig.addTodoDescriptionMaxLines,
                keyboardOptions = KeyboardOptions.Default.copy(
                    capitalization = KeyboardCapitalization.Sentences
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Padding(vertical = MaterialTheme.spacing.small)

            DatePickerField(
                date = formattedDate,
                onDateSelect = { date ->
                    store.dispatch(AddTodoAction.UpdateDueDate(date))
                }
            )

            Padding(vertical = MaterialTheme.spacing.small)

            TimePickerField(
                time = formattedTime,
                onTimeSelect = { hour, minute ->
                    store.dispatch(AddTodoAction.UpdateDueTime(hour, minute))
                }
            )
        }
    }
}

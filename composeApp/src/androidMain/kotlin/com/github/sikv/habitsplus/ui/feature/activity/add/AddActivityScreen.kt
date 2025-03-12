package com.github.sikv.habitsplus.ui.feature.activity.add

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
import com.github.sikv.habitsplus.R
import com.github.sikv.habitsplus.feature.activity.add.AddActivityAction
import com.github.sikv.habitsplus.feature.activity.add.AddActivityError
import com.github.sikv.habitsplus.feature.activity.add.AddActivityResult
import com.github.sikv.habitsplus.feature.activity.add.AddActivityResultEffect
import com.github.sikv.habitsplus.feature.activity.add.AddActivityState
import com.github.sikv.habitsplus.store.AppStore
import com.github.sikv.habitsplus.ui.components.DatePickerField
import com.github.sikv.habitsplus.ui.components.Padding
import com.github.sikv.habitsplus.ui.components.TopAppBar
import com.github.sikv.habitsplus.ui.theme.spacing
import com.github.sikv.habitsplus.util.DateTimeFormatter
import com.github.sikv.habitsplus.util.LocalDateTimeFormatter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.mapLatest
import org.koin.compose.koinInject

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun AddActivityScreen(
    onNavigateBack: () -> Unit,
    store: AppStore = koinInject()
) {
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }

    val state by store.observeState()
        .mapLatest { it.addActivityState }
        .collectAsStateWithLifecycle(AddActivityState())

    val resultEffect by store.observeSideEffect()
        .filterIsInstance<AddActivityResultEffect>()
        .collectAsStateWithLifecycle(null)

    var descriptionHasError by remember(state) { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        store.dispatch(AddActivityAction.Init)
    }

    LaunchedEffect(resultEffect) {
        resultEffect?.result?.let { result ->
            when (result) {
                AddActivityResult.Success -> onNavigateBack()
                is AddActivityResult.Failure -> {
                    descriptionHasError = when (result.error) {
                        AddActivityError.Unknown -> false
                        AddActivityError.EmptyDescription -> true
                    }
                    snackbarHostState.showSnackbar(result.error.localizedMessage(context))
                }
            }
        }
    }

    val dateTimeProvider = LocalDateTimeFormatter.current
    val formattedDate = remember(state) {
        dateTimeProvider.format(context, state.date, DateTimeFormatter.Pattern.MONTH_DAY_YEAR)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                onNavigateBack = onNavigateBack,
                title = stringResource(R.string.add_activity_screen_title),
                actions = {
                    Button(
                        onClick = {
                            store.dispatch(AddActivityAction.Save)
                        },
                        modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium)
                    ) {
                        Text(stringResource(R.string.action_save))
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
            DatePickerField(
                label = stringResource(R.string.add_activity_date_label),
                date = formattedDate,
                showClearButton = false,
                onDateSelect = { date ->
                    store.dispatch(AddActivityAction.UpdateDate(date ?: 0))
                }
            )

            Padding(vertical = MaterialTheme.spacing.medium)

            OutlinedTextField(
                value = state.description,
                onValueChange = { value ->
                    store.dispatch(
                        AddActivityAction.UpdateDescription(
                            value.take(state.descriptionMaxLength)
                    ))
                },
                label = { Text(stringResource(R.string.add_activity_description_label)) },
                isError = descriptionHasError,
                minLines = state.descriptionMinLines,
                keyboardOptions = KeyboardOptions.Default.copy(
                    capitalization = KeyboardCapitalization.Sentences
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

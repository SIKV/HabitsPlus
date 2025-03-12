package com.github.sikv.habitsplus.ui.feature.label.add

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
import com.github.sikv.habitsplus.feature.label.add.AddLabelAction
import com.github.sikv.habitsplus.feature.label.add.AddLabelError
import com.github.sikv.habitsplus.feature.label.add.AddLabelResult
import com.github.sikv.habitsplus.feature.label.add.AddLabelResultEffect
import com.github.sikv.habitsplus.feature.label.add.AddLabelState
import com.github.sikv.habitsplus.store.AppStore
import com.github.sikv.habitsplus.ui.components.ColorItemPicker
import com.github.sikv.habitsplus.ui.components.TopAppBar
import com.github.sikv.habitsplus.ui.theme.spacing
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.mapLatest
import org.koin.compose.koinInject

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun AddLabelScreen(
    onBackClick: () -> Unit,
    store: AppStore = koinInject()
) {
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }

    val state by store.observeState()
        .mapLatest { it.addLabelState }
        .collectAsStateWithLifecycle(AddLabelState())

    val resultEffect by store.observeSideEffect()
        .filterIsInstance<AddLabelResultEffect>()
        .collectAsStateWithLifecycle(null)

    var titleHasError by remember(state) { mutableStateOf(false) }

    LaunchedEffect(resultEffect) {
        resultEffect?.result?.let { result ->
            when (result) {
                AddLabelResult.Success -> onBackClick()
                is AddLabelResult.Failure -> {
                    titleHasError = when (result.error) {
                        AddLabelError.Unknown -> false
                        AddLabelError.EmptyTitle -> true
                    }
                    snackbarHostState.showSnackbar(result.error.localizedMessage(context))
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                onNavigateBack = onBackClick,
                title = stringResource(R.string.add_label_screen_title),
                actions = {
                    Button(
                        onClick = {
                            store.dispatch(AddLabelAction.Save)
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
            OutlinedTextField(
                value = state.title,
                onValueChange = { value ->
                    store.dispatch(
                        AddLabelAction.UpdateTitle(
                            value.take(DefaultConfig.addLabelTitleMaxLength)
                        ))
                },
                label = { Text(stringResource(R.string.add_label_title_label)) },
                trailingIcon = {
                    ColorItemPicker(
                        color = state.color,
                        onColorSelect = { color ->
                            store.dispatch(AddLabelAction.UpdateColor(color))
                        }
                    )
                },
                isError = titleHasError,
                maxLines = 1,
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    capitalization = KeyboardCapitalization.Sentences
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

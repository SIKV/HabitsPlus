package com.github.sikv.habitsplus.ui.feature.label.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.sikv.habitsplus.R
import com.github.sikv.habitsplus.data.model.LabelModel
import com.github.sikv.habitsplus.feature.label.list.LabelListAction
import com.github.sikv.habitsplus.feature.label.list.LabelListState
import com.github.sikv.habitsplus.store.AppStore
import com.github.sikv.habitsplus.ui.components.NoItems
import com.github.sikv.habitsplus.ui.components.TopAppBar
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.mapLatest
import org.koin.compose.koinInject

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun LabelListScreen(
    onBackClick: () -> Unit,
    onLabelClick: (LabelModel) -> Unit,
    onAddLabelClick: () -> Unit,
    store: AppStore = koinInject()
) {
    val state by store.observeState()
        .mapLatest {
            it.labelListState
        }
        .collectAsStateWithLifecycle(LabelListState())

    LaunchedEffect(Unit) {
        store.dispatch(LabelListAction.FetchAll)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                onNavigateBack = onBackClick,
                title = stringResource(R.string.label_list_screen_title)
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddLabelClick
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.label_list_add_label_content_desc)
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding)
        ) {
            if (state.isLoading) {
                CircularProgressIndicator()
            } else {
                if (state.labels.isEmpty()) {
                    NoItems()
                } else {
                    Content(
                        labels = state.labels,
                        onLabelClick = {
                            // TODO:
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun Content(
    labels: List<LabelModel>,
    onLabelClick: (LabelModel) -> Unit
) {
    LazyColumn {
        items(labels) { label ->
            LabelItem(
                label = label,
                onClick = {
                    onLabelClick(label)
                }
            )
        }
    }
}

package com.github.sikv.habitsplus.ui.feature.activity

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.github.sikv.habitsplus.R
import com.github.sikv.habitsplus.store.AppStore
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivityScreen(
    onNavigateToAddActivity: () -> Unit,
    store: AppStore = koinInject()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.activity_screen_title)) }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = onNavigateToAddActivity,
                icon = { Icon(Icons.Default.Add, stringResource(R.string.activity_add_activity_button_content_desc)) },
                text = { Text(stringResource(R.string.activity_add_activity_button)) }
            )
        }
    ) { innerPadding ->
        Text("Activity", modifier = Modifier.padding(innerPadding))
    }
}

package com.github.sikv.habitsplus.ui.feature.activity.list

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.github.sikv.habitsplus.R
import com.github.sikv.habitsplus.store.AppStore
import com.github.sikv.habitsplus.ui.feature.common.ScaffoldMenu
import com.github.sikv.habitsplus.ui.feature.common.ScaffoldMenuItem
import com.github.sikv.habitsplus.ui.feature.common.scaffoldMenuItems
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivityScreen(
    onMenuItemClick: (ScaffoldMenuItem) -> Unit,
    store: AppStore = koinInject()
) {

    // TODO:

    ScaffoldMenu(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.activity_screen_title)) }
            )
        },
        menuItems = scaffoldMenuItems,
        onMenuItemClick = onMenuItemClick
    ) {
        // TODO: Implement.
    }
}

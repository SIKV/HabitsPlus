package com.github.sikv.habitsplus.ui.feature.habits

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.github.sikv.habitsplus.R
import com.github.sikv.habitsplus.ui.feature.common.ScaffoldMenu
import com.github.sikv.habitsplus.ui.feature.common.ScaffoldMenuItem
import com.github.sikv.habitsplus.ui.feature.common.scaffoldMenuItems

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HabitsScreen(
    onMenuItemClick: (ScaffoldMenuItem) -> Unit
) {
    ScaffoldMenu(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.habits_screen_title)) }
            )
        },
        menuItems = scaffoldMenuItems,
        onMenuItemClick = onMenuItemClick
    ) {
        // TODO: Implement.
    }
}

package com.github.sikv.habitsplus.ui.feature.habits

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.github.sikv.habitsplus.ui.feature.common.ScaffoldMenuItem
import kotlinx.serialization.Serializable

@Serializable
data object HabitsRoute

fun NavGraphBuilder.habitsDestination(
    onMenuItemClick: (ScaffoldMenuItem) -> Unit
) {
    composable<HabitsRoute> {
        HabitsScreen(
            onMenuItemClick = onMenuItemClick
        )
    }
}

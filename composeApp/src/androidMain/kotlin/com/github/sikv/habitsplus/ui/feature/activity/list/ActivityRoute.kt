package com.github.sikv.habitsplus.ui.feature.activity.list

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.github.sikv.habitsplus.ui.feature.common.ScaffoldMenuItem
import kotlinx.serialization.Serializable

@Serializable
data object ActivityRoute

fun NavGraphBuilder.activityDestination(
    onMenuItemClick: (ScaffoldMenuItem) -> Unit
) {
    composable<ActivityRoute> {
        ActivityScreen(
            onMenuItemClick = onMenuItemClick
        )
    }
}

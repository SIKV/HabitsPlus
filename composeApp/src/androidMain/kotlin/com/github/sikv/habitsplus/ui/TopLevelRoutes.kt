package com.github.sikv.habitsplus.ui

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.ui.graphics.vector.ImageVector
import com.github.sikv.habitsplus.R
import kotlinx.serialization.Serializable

@Serializable
data object HabitsRootRoute

@Serializable
data object TodosRootRoute

@Serializable
data object ActivityRootRoute

@Serializable
data object MoreRootRoute

enum class TopLevelRoute(
    @StringRes val title: Int,
    val route: Any,
    val icon: ImageVector
) {
    HABITS(
        title = R.string.top_level_route_habits,
        route = HabitsRootRoute,
        icon = Icons.Default.ThumbUp
    ),
    TODOS(
        title = R.string.top_level_route_todos,
        route = TodosRootRoute,
        icon = Icons.Default.Done
    ),
    ACTIVITY(
        title = R.string.top_level_route_activity,
        route = ActivityRootRoute,
        icon = Icons.Default.Star
    ),
    MORE(
        title = R.string.top_level_route_more,
        route = MoreRootRoute,
        icon = Icons.Default.Menu
    )
}

val topLevelRoutes = TopLevelRoute.entries

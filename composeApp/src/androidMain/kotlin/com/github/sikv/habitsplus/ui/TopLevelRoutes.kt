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
data object HabitsRoute

@Serializable
data object TodosRoute

@Serializable
data object ActivityRoute

@Serializable
data object MoreRoute

data class TopLevelRoute<T : Any>(
    @StringRes val name: Int,
    val route: T,
    val icon: ImageVector
)

val topLevelRoutes = listOf(
    TopLevelRoute(R.string.top_level_route_habits, HabitsRoute, Icons.Default.ThumbUp),
    TopLevelRoute(R.string.top_level_route_todos, TodosRoute, Icons.Default.Done),
    TopLevelRoute(R.string.top_level_route_activity, ActivityRoute, Icons.Default.Star),
    TopLevelRoute(R.string.top_level_route_more, MoreRoute, Icons.Default.Menu)
)

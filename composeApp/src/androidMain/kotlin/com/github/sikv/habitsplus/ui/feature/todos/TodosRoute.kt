package com.github.sikv.habitsplus.ui.feature.todos

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.github.sikv.habitsplus.ui.feature.common.ScaffoldMenuItem
import kotlinx.serialization.Serializable

@Serializable
data object TodosRoute

fun NavGraphBuilder.todosDestination(
    onMenuItemClick: (ScaffoldMenuItem) -> Unit
) {
    composable<TodosRoute> {
        TodosScreen(
            onMenuItemClick = onMenuItemClick
        )
    }
}

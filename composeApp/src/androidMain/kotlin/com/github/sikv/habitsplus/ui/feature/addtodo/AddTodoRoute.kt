package com.github.sikv.habitsplus.ui.feature.addtodo

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object AddTodoRoute

fun NavGraphBuilder.addTodoDestination(
    onNavigateBack: () -> Unit
) {
    composable<AddTodoRoute> {
        AddTodoScreen(
            onNavigateBack = onNavigateBack
        )
    }
}

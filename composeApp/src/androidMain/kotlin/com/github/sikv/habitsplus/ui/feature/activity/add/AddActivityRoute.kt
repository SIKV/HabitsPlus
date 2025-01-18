package com.github.sikv.habitsplus.ui.feature.activity.add

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object AddActivityRoute

fun NavGraphBuilder.addActivityDestination(
    onNavigateBack: () -> Unit
) {
    composable<AddActivityRoute> {
        AddActivityScreen(
            onNavigateBack = onNavigateBack
        )
    }
}

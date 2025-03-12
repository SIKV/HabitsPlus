package com.github.sikv.habitsplus.ui.feature.label.add

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object AddLabelRoute

fun NavGraphBuilder.addLabelDestination(
    onBackClick: () -> Unit
) {
    composable<AddLabelRoute> {
        AddLabelScreen(
            onBackClick = onBackClick
        )
    }
}

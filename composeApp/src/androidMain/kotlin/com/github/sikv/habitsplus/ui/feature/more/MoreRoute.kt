package com.github.sikv.habitsplus.ui.feature.more

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object MoreRoute

fun NavGraphBuilder.moreDestination(
    onLabelsItemClick: () -> Unit
) {
    composable<MoreRoute> {
        MoreScreen(
            onLabelsItemClick = onLabelsItemClick
        )
    }
}

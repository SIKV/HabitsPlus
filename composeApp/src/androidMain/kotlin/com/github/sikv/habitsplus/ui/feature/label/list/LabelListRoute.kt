package com.github.sikv.habitsplus.ui.feature.label.list

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.github.sikv.habitsplus.data.label.LabelModel
import kotlinx.serialization.Serializable

@Serializable
data object LabelListRoute

fun NavGraphBuilder.labelListDestination(
    onBackClick: () -> Unit,
    onLabelClick: (LabelModel) -> Unit,
    onAddLabelClick: () -> Unit
) {
    composable<LabelListRoute> {
        LabelListScreen(
            onBackClick = onBackClick,
            onLabelClick = onLabelClick,
            onAddLabelClick = onAddLabelClick
        )
    }
}

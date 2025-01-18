package com.github.sikv.habitsplus.ui.feature.common

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.ui.graphics.vector.ImageVector
import com.github.sikv.habitsplus.R

enum class ScaffoldMenuItem(
    @StringRes val title: Int,
    val icon: ImageVector
) {
    ADD_HABIT(
        title = R.string.menu_item_add_habit,
        icon = Icons.Filled.ThumbUp
    ),
    ADD_TODO(
        title = R.string.menu_item_add_todo,
        icon = Icons.Filled.CheckCircle
    ),
    ADD_ACTIVITY(
        title = R.string.menu_item_add_activity,
        icon = Icons.Filled.Star
    )
}

val scaffoldMenuItems = ScaffoldMenuItem.entries

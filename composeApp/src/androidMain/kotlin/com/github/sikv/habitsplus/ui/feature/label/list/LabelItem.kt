package com.github.sikv.habitsplus.ui.feature.label.list

import androidx.compose.foundation.clickable
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.sikv.habitsplus.data.label.LabelModel
import com.github.sikv.habitsplus.ui.components.ColorItem

@Composable
fun LabelItem(
    label: LabelModel,
    onClick: () -> Unit
) {
    ListItem(
        leadingContent = {
            ColorItem(label.color)
        },
        headlineContent = {
            Text(label.title)
        },
        modifier = Modifier
            .clickable {
                onClick()
            }
    )
}

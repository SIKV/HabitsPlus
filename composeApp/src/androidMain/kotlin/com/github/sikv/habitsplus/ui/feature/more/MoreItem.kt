package com.github.sikv.habitsplus.ui.feature.more

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MoreItem(
    title: String,
    onClick: () -> Unit
) {
    ListItem(
        headlineContent = {
            Text(title)
        },
        trailingContent = {
            Icon(Icons.AutoMirrored.Default.KeyboardArrowRight, contentDescription = null)
        },
        modifier = Modifier
            .clickable {
                onClick()
            }
    )
}

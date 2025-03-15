package com.github.sikv.habitsplus.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.github.sikv.habitsplus.data.label.ColorVariant

enum class ColorItemSize(val dp: Dp) {
    NORMAL(24.dp),
    BIG(48.dp)
}

@Composable
fun ColorItem(
    color: ColorVariant,
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    size: ColorItemSize = ColorItemSize.NORMAL
) {
    val colorHex = if (isSystemInDarkTheme()) color.dark else color.light

    val colorInt = remember(colorHex) {
        try {
            Color(colorHex.toColorInt())
        } catch (e: Exception) {
            Color.Transparent
        }
    }

    Surface(
        shape = CircleShape,
        modifier = modifier
            .requiredSize(size.dp),
        color = colorInt,
        border = if (selected) BorderStroke(
            width = 3.dp,
            color = MaterialTheme.colorScheme.onSurface
        ) else null,
        content = {},
    )
}

package com.github.sikv.habitsplus.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Padding(horizontal: Dp = 0.dp, vertical: Dp = 0.dp) {
    Spacer(modifier = Modifier
        .padding(
            horizontal = horizontal,
            vertical = vertical
        )
    )
}

package com.github.sikv.habitsplus.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalSpacing provides Spacing()
    ) {
        MaterialTheme(content = content)
    }
}

val MaterialTheme.spacing: Spacing
    @Composable
    @ReadOnlyComposable
    get() = LocalSpacing.current

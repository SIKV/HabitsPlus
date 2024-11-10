package com.github.sikv.habitsplus.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.github.sikv.habitsplus.R

@Composable
fun ClearFieldButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_remove_circle_outline_24),
            contentDescription = stringResource(R.string.clear_button_content_desc)
        )
    }
}

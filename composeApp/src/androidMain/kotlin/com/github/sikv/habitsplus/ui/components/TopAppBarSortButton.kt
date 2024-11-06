package com.github.sikv.habitsplus.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.github.sikv.habitsplus.R

@Composable
fun TopAppBarSortButton(
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_sort_24),
            contentDescription = stringResource(R.string.sort_button_content_desc)
        )
    }
}

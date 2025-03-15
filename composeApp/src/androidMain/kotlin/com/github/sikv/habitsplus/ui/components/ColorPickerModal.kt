package com.github.sikv.habitsplus.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.github.sikv.habitsplus.R
import com.github.sikv.habitsplus.ui.theme.spacing
import com.github.sikv.habitsplus.data.label.ColorVariant

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ColorPickerModal(
    selectedColor: ColorVariant,
    colors: List<ColorVariant>,
    onDismiss: () -> Unit,
    onColorSelect: (ColorVariant) -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.action_cancel))
            }
        },
        text = {
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(
                    space = MaterialTheme.spacing.medium,
                    alignment = Alignment.CenterHorizontally
                ),
                verticalArrangement = Arrangement.spacedBy(
                    space = MaterialTheme.spacing.medium,
                    alignment = Alignment.CenterVertically
                )
            ) {
                colors.forEach { color ->
                    ColorItem(
                        color = color,
                        size = ColorItemSize.BIG,
                        selected = selectedColor == color,
                        modifier = Modifier.clickable {
                            onColorSelect(color)
                        }
                    )
                }
            }
        }
    )
}

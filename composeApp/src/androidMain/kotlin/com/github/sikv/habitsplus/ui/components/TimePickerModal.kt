package com.github.sikv.habitsplus.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.github.sikv.habitsplus.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerModal(
    onTimeSelect: (TimePickerState) -> Unit,
    onDismiss: () -> Unit
) {
    val state = rememberTimePickerState()

    AlertDialog(
        onDismissRequest = onDismiss,
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.action_cancel))
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onTimeSelect(state)
                }
            ) {
                Text(stringResource(R.string.action_ok))
            }
        },
        text = {
            TimePicker(
                state = state,
            )
        }
    )
}

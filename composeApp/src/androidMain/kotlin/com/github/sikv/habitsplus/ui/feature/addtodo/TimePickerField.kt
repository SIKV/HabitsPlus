package com.github.sikv.habitsplus.ui.feature.addtodo

import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.github.sikv.habitsplus.R
import com.github.sikv.habitsplus.ui.components.ClearFieldButton
import com.github.sikv.habitsplus.ui.components.TimePickerModal
import com.github.sikv.habitsplus.ui.theme.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerField(
    time: String,
    onTimeSelect: (hour: Int?, minute: Int?, is24hour: Boolean?) -> Unit
) {
    var showModal by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = time,
            onValueChange = { },
            label = { Text(stringResource(R.string.add_todo_due_time_label)) },
            trailingIcon = {
                Icon(
                    painter = painterResource(R.drawable.ic_time_24),
                    contentDescription = stringResource(R.string.add_todo_select_time_content_desc)
                )
            },
            readOnly = true,
            modifier = Modifier
                .weight(1f)
                .pointerInput(time) {
                    awaitEachGesture {
                        // Modifier.clickable doesn't work for text fields, so we use Modifier.pointerInput
                        // in the Initial pass to observe events before the text field consumes them
                        // in the Main pass.
                        // Source: https://developer.android.com/develop/ui/compose/components/datepickers
                        awaitFirstDown(pass = PointerEventPass.Initial)
                        val upEvent = waitForUpOrCancellation(pass = PointerEventPass.Initial)
                        if (upEvent != null) {
                            showModal = true
                        }
                    }
                }
        )
        if (time.isNotBlank()) {
            ClearFieldButton(
                onClick = {
                    onTimeSelect(null, null, null)
                },
                modifier = Modifier.padding(top = MaterialTheme.spacing.small)
            )
        }
    }

    if (showModal) {
        TimePickerModal(
            onTimeSelect = { timePickerState ->
                onTimeSelect(timePickerState.hour, timePickerState.minute, timePickerState.is24hour)
                showModal = false
            },
            onDismiss = { showModal = false }
        )
    }
}

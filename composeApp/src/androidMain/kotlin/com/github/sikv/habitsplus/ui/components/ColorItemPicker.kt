package com.github.sikv.habitsplus.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.github.sikv.habitsplus.data.label.ColorPalette
import com.github.sikv.habitsplus.data.label.ColorVariant

@Composable
fun ColorItemPicker(
    color: ColorVariant,
    onColorSelect: (color: ColorVariant) -> Unit
) {
    var showModal by remember { mutableStateOf(false) }

    ColorItem(
        color,
        modifier = Modifier.clickable {
            showModal = true
        }
    )

    if (showModal) {
        ColorPickerModal(
            selectedColor = color,
            colors = ColorPalette.getAll(),
            onDismiss =  { showModal = false },
            onColorSelect = { selectedColor ->
                onColorSelect(selectedColor)
                showModal = false
            }
        )
    }
}

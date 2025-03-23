package com.github.sikv.habitsplus.feature.label.add

import com.github.sikv.habitsplus.data.label.ColorPalette
import com.github.sikv.habitsplus.data.label.ColorVariant
import com.github.sikv.habitsplus.store.StoreState

private const val TITLE_MAX_LENGTH = 64

data class AddLabelState(
    val title: String = "",
    val color: ColorVariant = ColorPalette.default,
    val titleMaxLength: Int = TITLE_MAX_LENGTH
) : StoreState

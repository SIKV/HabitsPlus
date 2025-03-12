package com.github.sikv.habitsplus.feature.label.add

import com.github.sikv.habitsplus.DefaultConfig
import com.github.sikv.habitsplus.store.StoreState
import com.github.sikv.habitsplus.util.ColorPalette
import com.github.sikv.habitsplus.util.ColorVariant

data class AddLabelState(
    val title: String = "",
    val color: ColorVariant = ColorPalette.default,
    val titleMaxLength: Int = DefaultConfig.ADD_LABEL_TITLE_MAX_LENGTH
) : StoreState

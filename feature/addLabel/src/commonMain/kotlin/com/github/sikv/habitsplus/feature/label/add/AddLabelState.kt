package com.github.sikv.habitsplus.feature.label.add

import com.github.sikv.habitsplus.data.label.ColorPalette
import com.github.sikv.habitsplus.data.label.ColorVariant
import com.github.sikv.habitsplus.feature.common.DefaultConfig
import com.github.sikv.habitsplus.store.StoreState

data class AddLabelState(
    val title: String = "",
    val color: ColorVariant = ColorPalette.default,
    val titleMaxLength: Int = DefaultConfig.ADD_LABEL_TITLE_MAX_LENGTH
) : StoreState

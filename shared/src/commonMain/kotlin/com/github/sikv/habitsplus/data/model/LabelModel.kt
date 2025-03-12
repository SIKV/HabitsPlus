package com.github.sikv.habitsplus.data.model

import com.github.sikv.habitsplus.util.ColorVariant

data class LabelModel(
    val id: Long = AUTOINCREMENT_ID,
    val title: String,
    val color: ColorVariant
)

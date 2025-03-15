package com.github.sikv.habitsplus.data.label

import com.github.sikv.habitsplus.data.common.AUTOINCREMENT_ID

data class LabelModel(
    val id: Long = AUTOINCREMENT_ID,
    val title: String,
    val color: ColorVariant
)

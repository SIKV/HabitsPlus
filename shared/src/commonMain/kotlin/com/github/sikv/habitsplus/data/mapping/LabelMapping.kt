package com.github.sikv.habitsplus.data.mapping

import com.github.sikv.habitsplus.data.model.LabelModel
import com.github.sikv.habitsplus.util.ColorVariant

internal fun mapLabel(
    id: Long,
    title: String,
    lightColor: String,
    darkColor: String
): LabelModel {
    return LabelModel(
        id = id,
        title = title,
        color = ColorVariant(lightColor, darkColor)
    )
}

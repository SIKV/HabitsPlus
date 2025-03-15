package com.github.sikv.habitsplus.data.label

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

package com.github.sikv.habitsplus.data.mapping

import com.github.sikv.habitsplus.data.model.LabelModel

internal fun mapLabel(
    id: Long,
    title: String,
    color: String
): LabelModel {
    return LabelModel(
        id = id,
        title = title,
        color = color
    )
}

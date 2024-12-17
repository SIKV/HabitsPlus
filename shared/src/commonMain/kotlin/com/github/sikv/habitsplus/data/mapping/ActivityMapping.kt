package com.github.sikv.habitsplus.data.mapping

import com.github.sikv.habitsplus.data.model.ActivityModel
import com.github.sikv.habitsplus.data.model.Metadata

internal const val IMAGES_DELIMITER = ","

internal fun mapActivity(
    id: Long,
    description: String,
    images: String?,
    dateMs: Long,
    addedAtMs: Long,
    editedAtMs: Long?
): ActivityModel {
    return ActivityModel(
        id = id,
        description = description,
        images = images?.split(IMAGES_DELIMITER) ?: emptyList(),
        date = dateMs,
        metadata = Metadata(
            addedAt = addedAtMs,
            editedAt = editedAtMs
        )
    )
}

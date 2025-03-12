package com.github.sikv.habitsplus.data.source

import com.github.sikv.habitsplus.data.model.LabelModel
import com.github.sikv.habitsplus.util.ColorVariant

internal interface LabelsDataSource {
    fun insertLabel(
        title: String,
        color: ColorVariant
    ) : Boolean

    fun updateLabel(
        id: Long,
        title: String,
        color: ColorVariant
    ) : Boolean

    fun selectAllLabels(): List<LabelModel>
}

package com.github.sikv.habitsplus.data.source

import com.github.sikv.habitsplus.data.model.LabelModel

internal interface LabelsLocalDataSource {
    fun insertLabel(
        title: String,
        color: String
    ) : Boolean

    fun updateLabel(
        id: Long,
        title: String,
        color: String
    ) : Boolean

    fun selectAllLabels(): List<LabelModel>
}

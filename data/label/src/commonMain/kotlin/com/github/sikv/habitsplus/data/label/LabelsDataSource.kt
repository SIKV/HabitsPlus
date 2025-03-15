package com.github.sikv.habitsplus.data.label

interface LabelsDataSource {
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

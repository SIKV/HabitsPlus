package com.github.sikv.habitsplus.data.label

interface LabelsDataSource {
    suspend fun insertLabel(
        title: String,
        color: ColorVariant
    ) : Boolean

    suspend fun updateLabel(
        id: Long,
        title: String,
        color: ColorVariant
    ) : Boolean

    suspend fun selectAllLabels(): List<LabelModel>
}

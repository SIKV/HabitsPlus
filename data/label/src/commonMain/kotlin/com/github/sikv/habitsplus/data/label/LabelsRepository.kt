package com.github.sikv.habitsplus.data.label

interface LabelsRepository {
    suspend fun addLabel(label: LabelModel): Boolean
    suspend fun updateLabel(label: LabelModel): Boolean
    suspend fun getAllLabels(): List<LabelModel>
}

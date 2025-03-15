package com.github.sikv.habitsplus.data.label

interface LabelsRepository {
    fun addLabel(label: LabelModel): Boolean
    fun updateLabel(label: LabelModel): Boolean
    fun getAllLabels(): List<LabelModel>
}

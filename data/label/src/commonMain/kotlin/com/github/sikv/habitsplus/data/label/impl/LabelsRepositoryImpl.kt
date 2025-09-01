package com.github.sikv.habitsplus.data.label.impl

import com.github.sikv.habitsplus.data.label.LabelModel
import com.github.sikv.habitsplus.data.label.LabelsDataSource
import com.github.sikv.habitsplus.data.label.LabelsRepository

class LabelsRepositoryImpl(
    private val labelsDataSource: LabelsDataSource,
) : LabelsRepository {

    override suspend fun addLabel(label: LabelModel): Boolean {
        return labelsDataSource.insertLabel(
            title = label.title,
            color = label.color
        )
    }

    override suspend fun updateLabel(label: LabelModel): Boolean {
        return labelsDataSource.updateLabel(
            id = label.id,
            title = label.title,
            color = label.color
        )
    }

    override suspend fun getAllLabels(): List<LabelModel> {
        return labelsDataSource.selectAllLabels()
    }
}

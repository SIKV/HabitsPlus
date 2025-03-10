package com.github.sikv.habitsplus.data.repository.impl

import com.github.sikv.habitsplus.data.model.LabelModel
import com.github.sikv.habitsplus.data.repository.LabelsRepository
import com.github.sikv.habitsplus.data.source.LabelsDataSource

internal class LabelsRepositoryImpl(
    private val labelsDataSource: LabelsDataSource,
) : LabelsRepository {

    override fun addLabel(label: LabelModel): Boolean {
        return labelsDataSource.insertLabel(
            title = label.title,
            color = label.color
        )
    }

    override fun updateLabel(label: LabelModel): Boolean {
        return labelsDataSource.updateLabel(
            id = label.id,
            title = label.title,
            color = label.color
        )
    }

    override fun getAllLabels(): List<LabelModel> {
        return labelsDataSource.selectAllLabels()
    }
}

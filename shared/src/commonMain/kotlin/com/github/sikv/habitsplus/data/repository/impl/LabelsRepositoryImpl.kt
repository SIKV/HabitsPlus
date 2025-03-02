package com.github.sikv.habitsplus.data.repository.impl

import com.github.sikv.habitsplus.data.model.LabelModel
import com.github.sikv.habitsplus.data.repository.LabelsRepository
import com.github.sikv.habitsplus.data.source.LabelsLocalDataSource

internal class LabelsRepositoryImpl(
    private val labelsLocalDataSource: LabelsLocalDataSource,
) : LabelsRepository {

    override fun addLabel(label: LabelModel): Boolean {
        return labelsLocalDataSource.insertLabel(
            title = label.title,
            color = label.color
        )
    }

    override fun updateLabel(label: LabelModel): Boolean {
        return labelsLocalDataSource.updateLabel(
            id = label.id,
            title = label.title,
            color = label.color
        )
    }

    override fun getAllLabels(): List<LabelModel> {
        return labelsLocalDataSource.selectAllLabels()
    }
}

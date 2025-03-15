package com.github.sikv.habitsplus.data.label.impl

import com.github.sikv.habitsplus.data.label.LabelModel
import com.github.sikv.habitsplus.data.label.LabelsRepository
import com.github.sikv.habitsplus.data.label.LabelsDataSource

class LabelsRepositoryImpl(
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

package com.github.sikv.habitsplus.data.repository

import com.github.sikv.habitsplus.data.model.LabelModel

internal interface LabelsRepository {

    fun addLabel(label: LabelModel): Boolean
    fun updateLabel(label: LabelModel): Boolean
    fun getAllLabels(): List<LabelModel>
}

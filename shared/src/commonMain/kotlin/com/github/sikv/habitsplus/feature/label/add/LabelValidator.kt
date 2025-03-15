package com.github.sikv.habitsplus.feature.label.add

import com.github.sikv.habitsplus.data.label.LabelModel
import com.github.sikv.habitsplus.util.ModelValidator

internal class LabelValidator : ModelValidator<LabelModel, AddLabelError?> {

    override fun checkErrors(model: LabelModel): AddLabelError? {
        return if (model.title.isBlank()) {
            AddLabelError.EmptyTitle
        } else {
            null
        }
    }
}

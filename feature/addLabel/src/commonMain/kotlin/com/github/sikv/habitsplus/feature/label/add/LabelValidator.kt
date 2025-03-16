package com.github.sikv.habitsplus.feature.label.add

import com.github.sikv.habitsplus.data.label.LabelModel
import com.github.sikv.habitsplus.feature.common.ModelValidator

class LabelValidator : ModelValidator<LabelModel, AddLabelError?> {

    override fun checkErrors(model: LabelModel): AddLabelError? {
        return if (model.title.isBlank()) {
            AddLabelError.EmptyTitle
        } else {
            null
        }
    }
}

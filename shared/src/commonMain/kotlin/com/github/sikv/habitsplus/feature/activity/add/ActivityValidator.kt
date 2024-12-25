package com.github.sikv.habitsplus.feature.activity.add

import com.github.sikv.habitsplus.data.model.ActivityModel
import com.github.sikv.habitsplus.util.ModelValidator

internal class ActivityValidator : ModelValidator<ActivityModel, AddActivityError?> {

    override fun checkErrors(model: ActivityModel): AddActivityError? {
        return if (model.description.isBlank()) {
            AddActivityError.EmptyDescription
        } else {
            null
        }
    }
}

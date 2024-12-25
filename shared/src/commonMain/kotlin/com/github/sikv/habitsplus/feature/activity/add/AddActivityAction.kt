package com.github.sikv.habitsplus.feature.activity.add

import com.github.sikv.habitsplus.data.model.Timestamp
import com.github.sikv.habitsplus.store.Action

sealed class AddActivityAction : Action {

    data object Init : AddActivityAction()

    data class UpdateDate(
        val date: Timestamp
    ) : AddActivityAction()

    data class UpdateDescription(
        val description: String
    ) : AddActivityAction()

    data class Update(
        val date: Timestamp,
        val description: String
    ) : AddActivityAction()

    data object Save : AddActivityAction()
}

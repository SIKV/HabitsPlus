package com.github.sikv.habitsplus.feature.label.add

import com.github.sikv.habitsplus.store.Action

sealed class AddLabelAction : Action {

    data class UpdateTitle(
        val title: String
    ) : AddLabelAction()

    data class UpdateColor(
        val color: String
    ) : AddLabelAction()

    data class Update(
        val title: String,
        val color: String
    ) : AddLabelAction()

    data object Save : AddLabelAction()
}

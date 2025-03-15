package com.github.sikv.habitsplus.feature.label.add

import com.github.sikv.habitsplus.store.Action
import com.github.sikv.habitsplus.data.label.ColorVariant

sealed class AddLabelAction : Action {

    data class UpdateTitle(
        val title: String
    ) : AddLabelAction()

    data class UpdateColor(
        val color: ColorVariant
    ) : AddLabelAction()

    data class Update(
        val title: String,
        val color: ColorVariant
    ) : AddLabelAction()

    data object Save : AddLabelAction()
}

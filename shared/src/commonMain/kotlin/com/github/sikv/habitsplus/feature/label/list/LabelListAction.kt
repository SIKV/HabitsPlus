package com.github.sikv.habitsplus.feature.label.list

import com.github.sikv.habitsplus.data.model.LabelModel
import com.github.sikv.habitsplus.store.Action

sealed class LabelListAction : Action {

    data object FetchAll : LabelListAction()

    data class UpdateLoading(
        val isLoading: Boolean
    ) : LabelListAction()

    data class UpdateList(
        val labels: List<LabelModel>
    ) : LabelListAction()
}

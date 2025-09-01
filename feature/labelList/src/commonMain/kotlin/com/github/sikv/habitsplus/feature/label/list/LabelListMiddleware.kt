package com.github.sikv.habitsplus.feature.label.list

import com.github.sikv.habitsplus.data.label.LabelsRepository
import com.github.sikv.habitsplus.store.Action
import com.github.sikv.habitsplus.store.AppMiddleware
import com.github.sikv.habitsplus.store.AppState
import com.github.sikv.habitsplus.store.Dispatcher

class LabelListMiddleware(
    private val labelsRepository: LabelsRepository
) : AppMiddleware {

    override suspend fun invoke(state: AppState, action: Action, dispatcher: Dispatcher) {
        when (action) {
            is LabelListAction.FetchAll -> handleFetchAllAction(dispatcher)
        }
    }

    private suspend fun handleFetchAllAction(dispatcher: Dispatcher) {
        dispatcher(LabelListAction.UpdateLoading(isLoading = true))

        val labels = labelsRepository.getAllLabels()

        dispatcher(
            LabelListAction.UpdateList(
                labels = labels
            )
        )
    }
}

package com.github.sikv.habitsplus.feature.label.list

import com.github.sikv.habitsplus.data.repository.LabelsRepository
import com.github.sikv.habitsplus.store.Action
import com.github.sikv.habitsplus.store.AppMiddleware
import com.github.sikv.habitsplus.store.AppState
import com.github.sikv.habitsplus.store.Dispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

internal class LabelListMiddleware(
    private val labelsRepository: LabelsRepository
) : AppMiddleware {

    override suspend fun invoke(state: AppState, action: Action, dispatcher: Dispatcher) {
        withContext(Dispatchers.IO) {
            when (action) {
                is LabelListAction.FetchAll -> handleFetchAllAction(dispatcher)
            }
        }
    }

    private fun handleFetchAllAction(dispatcher: Dispatcher) {
        dispatcher(LabelListAction.UpdateLoading(isLoading = true))

        val labels = labelsRepository.getAllLabels()

        dispatcher(
            LabelListAction.UpdateList(
                labels = labels
            )
        )
    }
}

package com.github.sikv.habitsplus.feature.label.add

import com.github.sikv.habitsplus.data.label.LabelModel
import com.github.sikv.habitsplus.data.label.LabelsRepository
import com.github.sikv.habitsplus.store.Action
import com.github.sikv.habitsplus.store.AppMiddleware
import com.github.sikv.habitsplus.store.AppState
import com.github.sikv.habitsplus.store.Dispatcher
import com.github.sikv.habitsplus.store.EmitSideEffectAction
import com.github.sikv.habitsplus.util.ModelValidator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

internal class AddLabelMiddleware(
    private val labelsRepository: LabelsRepository,
    private val validator: ModelValidator<LabelModel, AddLabelError?>,
) : AppMiddleware {

    override suspend fun invoke(state: AppState, action: Action, dispatcher: Dispatcher) {
        withContext(Dispatchers.IO) {
            when (action) {
                AddLabelAction.Save -> handleSaveAction(state.addLabelState, dispatcher)
            }
        }
    }

    private fun handleSaveAction(state: AddLabelState, dispatcher: Dispatcher) {
        val newLabel = LabelModel(
            title = state.title.trim(),
            color = state.color
        )

        val validationError = validator.checkErrors(newLabel)

        if (validationError == null) {
            val result = if (labelsRepository.addLabel(newLabel)) {
                AddLabelResult.Success
            } else {
                AddLabelResult.Failure()
            }
            dispatcher(EmitSideEffectAction(AddLabelResultEffect(result)))
        } else {
            val effect = AddLabelResultEffect(AddLabelResult.Failure(validationError))
            dispatcher(EmitSideEffectAction(effect))
        }
    }
}

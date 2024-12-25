package com.github.sikv.habitsplus.feature.activity.add

import com.github.sikv.habitsplus.data.model.ActivityModel
import com.github.sikv.habitsplus.data.repository.ActivitiesRepository
import com.github.sikv.habitsplus.store.Action
import com.github.sikv.habitsplus.store.AppMiddleware
import com.github.sikv.habitsplus.store.AppState
import com.github.sikv.habitsplus.store.Dispatcher
import com.github.sikv.habitsplus.store.EmitSideEffectAction
import com.github.sikv.habitsplus.util.DateTimeUtils
import com.github.sikv.habitsplus.util.ModelValidator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

internal class AddActivityMiddleware(
    private val activitiesRepository: ActivitiesRepository,
    private val validator: ModelValidator<ActivityModel, AddActivityError?>,
    private val dateTimeUtils: DateTimeUtils,
) : AppMiddleware {

    override suspend fun invoke(state: AppState, action: Action, dispatcher: Dispatcher) {
        withContext(Dispatchers.IO) {
            when (action) {
                AddActivityAction.Init -> handleInitAction(dispatcher)
                AddActivityAction.Save -> handleSaveAction(state.addActivityState, dispatcher)
            }
        }
    }

    private fun handleInitAction(dispatcher: Dispatcher) {
        dispatcher(AddActivityAction.UpdateDate(dateTimeUtils.currentTimestamp()))
    }

    private fun handleSaveAction(state: AddActivityState, dispatcher: Dispatcher) {
        val newActivity = ActivityModel(
            description = state.description.trim(),
            images = emptyList(), // TODO: Not supported yet.
            date = state.date
        )

        val validationError = validator.checkErrors(newActivity)

        if (validationError == null) {
            val result = if (activitiesRepository.addActivity(newActivity)) {
                AddActivityResult.Success
            } else {
                AddActivityResult.Failure()
            }
            dispatcher(EmitSideEffectAction(AddActivityResultEffect(result)))
        } else {
            val effect = AddActivityResultEffect(AddActivityResult.Failure(validationError))
            dispatcher(EmitSideEffectAction(effect))
        }
    }
}

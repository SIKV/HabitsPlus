package com.github.sikv.habitsplus.feature.activity.list

import com.github.sikv.habitsplus.data.common.DateTimeUtils
import com.github.sikv.habitsplus.data.model.ActivityModel
import com.github.sikv.habitsplus.data.repository.ActivitiesRepository
import com.github.sikv.habitsplus.store.Action
import com.github.sikv.habitsplus.store.AppMiddleware
import com.github.sikv.habitsplus.store.AppState
import com.github.sikv.habitsplus.store.Dispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

internal class ActivityListMiddleware(
    private val activitiesRepository: ActivitiesRepository,
    private val dateTimeUtils: DateTimeUtils
) : AppMiddleware {

    override suspend fun invoke(state: AppState, action: Action, dispatcher: Dispatcher) {
        withContext(Dispatchers.IO) {
            when (action) {
                is ActivityListAction.Init -> handleInitAction(dispatcher)
                is ActivityListAction.FetchAll -> handleFetchAllAction(action, dispatcher)
            }
        }
    }

    private fun handleInitAction(dispatcher: Dispatcher) {
        val currentYear = dateTimeUtils.currentYear()

        val activitiesYears = activitiesRepository.getActivitiesYears().toMutableSet().apply {
            // Always add the current year.
            add(currentYear)
        }

        dispatcher(ActivityListAction.UpdateYearsFilter(yearsFilter = activitiesYears))
        dispatcher(ActivityListAction.FetchAll(year = currentYear))
    }

    private fun handleFetchAllAction(action: ActivityListAction.FetchAll, dispatcher: Dispatcher) {
        dispatcher(ActivityListAction.UpdateLoading(isLoading = true))
        val activities = groupByMonth(activitiesRepository.getActivities(action.year))

        dispatcher(ActivityListAction.UpdateList(
            selectedYear = action.year,
            activities = activities
        ))
    }

    private fun groupByMonth(activities: List<ActivityModel>): List<ActivityGroup> {
        val grouped = activities.groupBy {
            dateTimeUtils.getMonthNumber(it.date)
        }
        return grouped.map { (key, value) ->
            ActivityGroup(
                monthNumber = key,
                activities = value
            )
        }
    }
}

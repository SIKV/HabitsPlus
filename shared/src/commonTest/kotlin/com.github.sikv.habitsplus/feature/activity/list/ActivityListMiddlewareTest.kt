package com.github.sikv.habitsplus.feature.activity.list

import com.github.sikv.habitsplus.data.repository.ActivitiesRepository
import com.github.sikv.habitsplus.store.AppState
import com.github.sikv.habitsplus.store.Dispatcher
import com.github.sikv.habitsplus.util.DateTimeUtils
import com.github.sikv.habitsplus.util.DateTimeUtilsImpl
import com.github.sikv.habitsplus.util.testActivities
import kotlinx.coroutines.test.runTest
import org.kodein.mock.Mocker
import org.kodein.mock.UsesMocks
import org.kodein.mock.generated.mock
import kotlin.test.Test
import kotlin.test.assertTrue

@UsesMocks(ActivitiesRepository::class, DateTimeUtils::class)
class ActivityListMiddlewareTest {

    @Test
    fun initAction_shouldDispatchFetchAllAction() = runTest {
        // GIVEN
        val mocker = Mocker()

        val activitiesRepository = mocker.mock<ActivitiesRepository>()

        val dateTimeUtils = mocker.mock<DateTimeUtils>()
        mocker.every { dateTimeUtils.currentYear() } returns 2025

        val middleware = ActivityListMiddleware(
            activitiesRepository = activitiesRepository,
            dateTimeUtils = dateTimeUtils
        )

        val appState = AppState.emptyState
        val initAction = ActivityListAction.Init

        val dispatcher: Dispatcher = { action ->
            // THEN
            assertTrue(action is ActivityListAction.FetchAll && action.year == 2025)
        }

        // WHEN
        middleware.invoke(appState, initAction, dispatcher)

        // THEN
        mocker.verify {
            dateTimeUtils.currentYear()
        }
    }

    @Test
    fun fetchAllAction_shouldDispatchUpdateListAction() = runTest {
        // GIVEN
        val mocker = Mocker()

        val activitiesRepository = mocker.mock<ActivitiesRepository>()
        mocker.every { activitiesRepository.getActivities(isAny()) } returns testActivities

        val dateTimeUtils = DateTimeUtilsImpl()

        val middleware = ActivityListMiddleware(
            activitiesRepository = activitiesRepository,
            dateTimeUtils = dateTimeUtils
        )

        val appState = AppState.emptyState
        val fetchAllAction = ActivityListAction.FetchAll(2025)

        var updateLoadingCalled = false
        var updateListCalled = false

        val dispatcher: Dispatcher = { action ->
            if (action is ActivityListAction.UpdateLoading && action.isLoading) {
                updateLoadingCalled = true
            } else if (action is ActivityListAction.UpdateList) { // TODO: Check list grouping.
                updateListCalled = true
            }
        }

        // WHEN
        middleware.invoke(appState, fetchAllAction, dispatcher)

        // THEN
        assertTrue(updateLoadingCalled)
        assertTrue(updateListCalled)

        mocker.verify {
            activitiesRepository.getActivities(2025)
        }
    }
}

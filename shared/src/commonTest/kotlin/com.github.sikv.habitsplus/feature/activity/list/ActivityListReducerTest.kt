package com.github.sikv.habitsplus.feature.activity.list

import com.github.sikv.habitsplus.data.model.ActivityModel
import kotlin.test.Test
import kotlin.test.assertEquals

class ActivityListReducerTest {

    @Test
    fun updateLoadingAction_shouldUpdateState() {
        // GIVEN
        val state = ActivityListState()
        val action = ActivityListAction.UpdateLoading(isLoading = true)

        // WHEN
        val newState = activityListReducer(state, action)

        // THEN
        assertEquals(action.isLoading, newState.isLoading)
    }

    @Test
    fun updateListAction_shouldUpdateState() {
        // GIVEN
        val testActivities = listOf(
            ActivityGroup(
                monthNumber = 3,
                activities = listOf(
                    ActivityModel(
                        description = "test",
                        images = listOf("1", "2"),
                        date = 123L
                    )
                )
            )
        )

        val state = ActivityListState()
        val action = ActivityListAction.UpdateList(activities = testActivities)

        // WHEN
        val newState = activityListReducer(state, action)

        // THEN
        assertEquals(false, newState.isLoading)
        assertEquals(action.activities, newState.activities)
    }
}

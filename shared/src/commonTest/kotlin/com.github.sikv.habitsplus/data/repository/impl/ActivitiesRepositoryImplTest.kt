package com.github.sikv.habitsplus.data.repository.impl

import com.github.sikv.habitsplus.data.mapping.IMAGES_DELIMITER
import com.github.sikv.habitsplus.data.model.ActivityModel
import com.github.sikv.habitsplus.data.source.ActivitiesLocalDataSource
import com.github.sikv.habitsplus.util.MockDateTimeUtils
import com.github.sikv.habitsplus.util.testActivities
import org.kodein.mock.Mocker
import org.kodein.mock.UsesMocks
import org.kodein.mock.generated.mock
import kotlin.test.Test
import kotlin.test.assertEquals

@UsesMocks(ActivitiesLocalDataSource::class)
class ActivitiesRepositoryImplTest {

    @Test
    fun addActivity_updatesLocalDataSource() {
        // GIVEN
        val mocker = Mocker()

        val localDataSource = mocker.mock<ActivitiesLocalDataSource>()
        mocker.every {
            localDataSource.insertActivity(isAny(), isAny(), isAny(), isAny())
        } returns true

        val mockDateTimeUtils = MockDateTimeUtils()

        val repo = ActivitiesRepositoryImpl(
            activitiesLocalDataSource = localDataSource,
            dateTimeUtils = mockDateTimeUtils
        )

        val activity = ActivityModel(
            description = "Test",
            images = emptyList(),
            date = 1234
        )

        // WHEN
        repo.addActivity(activity)

        // THEN
        mocker.verify {
            localDataSource.insertActivity(
                description = activity.description,
                dateMs = activity.date,
                images = activity.images.joinToString(IMAGES_DELIMITER),
                addedAtMs = mockDateTimeUtils.testTimeMillis
            )
        }
    }

    @Test
    fun updateActivity_updatesLocalDataSource() {
        // GIVEN
        val mocker = Mocker()

        val localDataSource = mocker.mock<ActivitiesLocalDataSource>()
        mocker.every {
            localDataSource.updateActivity(isAny(), isAny(), isAny(), isAny(), isAny())
        } returns true

        val mockDateTimeUtils = MockDateTimeUtils()

        val repo = ActivitiesRepositoryImpl(
            activitiesLocalDataSource = localDataSource,
            dateTimeUtils = mockDateTimeUtils
        )

        val activity = ActivityModel(
            description = "Test",
            images = listOf("1", "2"),
            date = 1234
        )

        // WHEN
        repo.updateActivity(activity)

        // THEN
        mocker.verify {
            localDataSource.updateActivity(
                id = activity.id,
                description = activity.description,
                images = activity.images.joinToString(IMAGES_DELIMITER),
                dateMs = activity.date,
                editedAtMs = mockDateTimeUtils.testTimeMillis
            )
        }
    }

    @Test
    fun getActivities_returnsData() {
        // GIVEN
        val mocker = Mocker()

        val localDataSource = mocker.mock<ActivitiesLocalDataSource>()
        mocker.every { localDataSource.selectAllActivities() } returns testActivities

        val mockDateTimeUtils = MockDateTimeUtils()

        val repo = ActivitiesRepositoryImpl(
            activitiesLocalDataSource = localDataSource,
            dateTimeUtils = mockDateTimeUtils
        )

        // WHEN
        val actualActivities = repo.getActivities()

        // THEN
        val expectedActivities = testActivities
        assertEquals(expectedActivities, actualActivities)
    }
}

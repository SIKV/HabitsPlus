package com.github.sikv.habitsplus.data.repository.impl

import com.github.sikv.habitsplus.data.mapping.IMAGES_DELIMITER
import com.github.sikv.habitsplus.data.model.ActivityModel
import com.github.sikv.habitsplus.data.source.ActivitiesDataSource
import com.github.sikv.habitsplus.util.FakeDateTimeUtils
import com.github.sikv.habitsplus.util.testActivities
import com.github.sikv.habitsplus.util.testActivitiesYears
import org.kodein.mock.Mocker
import org.kodein.mock.UsesMocks
import org.kodein.mock.generated.mock
import kotlin.test.Test
import kotlin.test.assertEquals

@UsesMocks(ActivitiesDataSource::class)
class ActivitiesRepositoryImplTest {

    @Test
    fun addActivity_updatesLocalDataSource() {
        // GIVEN
        val mocker = Mocker()

        val localDataSource = mocker.mock<ActivitiesDataSource>()
        mocker.every {
            localDataSource.insertActivity(isAny(), isAny(), isAny(), isAny())
        } returns true

        val mockDateTimeUtils = FakeDateTimeUtils()

        val repo = ActivitiesRepositoryImpl(
            activitiesDataSource = localDataSource,
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

        val localDataSource = mocker.mock<ActivitiesDataSource>()
        mocker.every {
            localDataSource.updateActivity(isAny(), isAny(), isAny(), isAny(), isAny())
        } returns true

        val mockDateTimeUtils = FakeDateTimeUtils()

        val repo = ActivitiesRepositoryImpl(
            activitiesDataSource = localDataSource,
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

        val year = 2025

        val localDataSource = mocker.mock<ActivitiesDataSource>()
        mocker.every { localDataSource.selectActivities(year) } returns testActivities

        val repo = ActivitiesRepositoryImpl(
            activitiesDataSource = localDataSource,
            dateTimeUtils = FakeDateTimeUtils()
        )

        // WHEN
        val actualActivities = repo.getActivities(year)

        // THEN
        assertEquals(testActivities, actualActivities)
    }

    @Test
    fun getActivitiesYears_returnsData() {
        // GIVEN
        val mocker = Mocker()

        val localDataSource = mocker.mock<ActivitiesDataSource>()
        mocker.every { localDataSource.selectActivitiesYears() } returns testActivitiesYears

        val repo = ActivitiesRepositoryImpl(
            activitiesDataSource = localDataSource,
            dateTimeUtils = FakeDateTimeUtils()
        )

        // WHEN
        val actualActivitiesYears = repo.getActivitiesYears()

        // THEN
        assertEquals(testActivitiesYears, actualActivitiesYears)
    }
}

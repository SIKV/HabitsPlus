package com.github.sikv.habitsplus.data.label.impl

import com.github.sikv.habitsplus.data.label.ColorVariant
import com.github.sikv.habitsplus.data.label.LabelModel
import com.github.sikv.habitsplus.data.label.LabelsDataSource
import com.github.sikv.habitsplus.data.label.LabelsDatabaseManager
import com.github.sikv.habitsplus.data.label.mapLabel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class LabelsLocalDataSourceImpl(
    private val database: LabelsDatabaseManager,
) : LabelsDataSource {

    override suspend fun insertLabel(title: String, color: ColorVariant): Boolean =
        withContext(Dispatchers.IO) {
            database.dbQuery
                .insertLabel(
                    title = title,
                    light_color = color.light,
                    dark_color = color.dark
                )

            // TODO: Update to return false in case of any error.
            true
        }

    override suspend fun updateLabel(id: Long, title: String, color: ColorVariant): Boolean =
        withContext(Dispatchers.IO) {
            database.dbQuery
                .updateLabel(
                    title = title,
                    light_color = color.light,
                    dark_color = color.dark,
                    id = id
                )

            // TODO: Update to return false in case of any error.
            true
        }

    override suspend fun selectAllLabels(): List<LabelModel> =
        withContext(Dispatchers.IO) {
            database.dbQuery
                .selectAllLabels(::mapLabel)
                .executeAsList()
        }
}

package com.github.sikv.habitsplus.data.source.impl

import com.github.sikv.habitsplus.data.mapping.mapLabel
import com.github.sikv.habitsplus.data.model.LabelModel
import com.github.sikv.habitsplus.data.source.LabelsDataSource
import com.github.sikv.habitsplus.database.LabelsDatabaseManager

internal class LabelsLocalDataSourceImpl(
    private val database: LabelsDatabaseManager,
) : LabelsDataSource {

    override fun insertLabel(title: String, color: String): Boolean {
        try {
            database.dbQuery
                .insertLabel(
                    title = title,
                    color = color
                )
            return true
        } catch (e: Exception) {
            // TODO: Print log.
            return false
        }
    }

    override fun updateLabel(id: Long, title: String, color: String): Boolean {
        try {
            database.dbQuery
                .updateLabel(
                    title = title,
                    color = color,
                    id = id
                )
            return true
        } catch (e: Exception) {
            // TODO: Print log.
            return false
        }
    }

    override fun selectAllLabels(): List<LabelModel> {
        return database.dbQuery
            .selectAllLabels(::mapLabel)
            .executeAsList()
    }
}

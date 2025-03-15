package com.github.sikv.habitsplus.data.label.impl

import com.github.sikv.habitsplus.data.label.mapLabel
import com.github.sikv.habitsplus.data.label.LabelModel
import com.github.sikv.habitsplus.data.label.LabelsDataSource
import com.github.sikv.habitsplus.data.label.LabelsDatabaseManager
import com.github.sikv.habitsplus.data.label.ColorVariant

class LabelsLocalDataSourceImpl(
    private val database: LabelsDatabaseManager,
) : LabelsDataSource {

    override fun insertLabel(title: String, color: ColorVariant): Boolean {
        try {
            database.dbQuery
                .insertLabel(
                    title = title,
                    light_color = color.light,
                    dark_color = color.dark
                )
            return true
        } catch (e: Exception) {
            // TODO: Print log.
            return false
        }
    }

    override fun updateLabel(id: Long, title: String, color: ColorVariant): Boolean {
        try {
            database.dbQuery
                .updateLabel(
                    title = title,
                    light_color = color.light,
                    dark_color = color.dark,
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

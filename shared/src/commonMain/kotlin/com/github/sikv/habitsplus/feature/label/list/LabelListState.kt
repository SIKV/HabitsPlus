package com.github.sikv.habitsplus.feature.label.list

import com.github.sikv.habitsplus.data.label.LabelModel
import com.github.sikv.habitsplus.store.StoreState

data class LabelListState(
    val isLoading: Boolean = false,
    val labels: List<LabelModel> = emptyList(),
): StoreState

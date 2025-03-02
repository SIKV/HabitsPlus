package com.github.sikv.habitsplus.feature.label.add

import com.github.sikv.habitsplus.store.StoreState

data class AddLabelState(
    val title: String = "",
    val color: String = ""
) : StoreState

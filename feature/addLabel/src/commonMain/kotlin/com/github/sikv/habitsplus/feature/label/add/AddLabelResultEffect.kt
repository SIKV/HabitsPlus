package com.github.sikv.habitsplus.feature.label.add

import com.github.sikv.habitsplus.store.ResultEffect

data class AddLabelResultEffect(
    override val result: AddLabelResult
) : ResultEffect<AddLabelResult>(result)

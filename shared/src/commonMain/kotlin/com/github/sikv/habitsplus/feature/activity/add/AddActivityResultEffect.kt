package com.github.sikv.habitsplus.feature.activity.add

import com.github.sikv.habitsplus.store.ResultEffect

data class AddActivityResultEffect(
    override val result: AddActivityResult
) : ResultEffect<AddActivityResult>(result)

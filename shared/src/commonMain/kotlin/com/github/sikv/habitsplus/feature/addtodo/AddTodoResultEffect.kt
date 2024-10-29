package com.github.sikv.habitsplus.feature.addtodo

import com.github.sikv.habitsplus.store.Effect

data class AddTodoHandleResultEffect(
    val result: AddTodoResult
) : Effect

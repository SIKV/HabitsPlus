package com.github.sikv.habitsplus.feature.todo.add

import com.github.sikv.habitsplus.store.ResultEffect

data class AddTodoResultEffect(
    override val result: AddTodoResult
) : ResultEffect<AddTodoResult>(result)

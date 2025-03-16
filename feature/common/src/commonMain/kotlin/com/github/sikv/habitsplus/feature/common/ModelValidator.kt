package com.github.sikv.habitsplus.feature.common

interface ModelValidator<M, E> {
    fun checkErrors(model: M): E
}

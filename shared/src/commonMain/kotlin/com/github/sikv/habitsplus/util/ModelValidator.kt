package com.github.sikv.habitsplus.util

interface ModelValidator<M, E> {

    fun checkErrors(model: M): E
}

package com.github.sikv.habitsplus.store

abstract class ResultEffect<R>(
    open val result: R
) : Effect

package com.github.sikv.habitsplus.store

interface Middleware<S> {
    suspend fun invoke(state: S, action: Action, dispatcher: Dispatcher)
}

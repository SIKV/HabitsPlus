package com.github.sikv.habitsplus.store

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

interface State

interface Action

typealias Dispatcher = (action: Action) -> Unit

typealias Reducer<S, A> = (state: S, action: A) -> S

typealias Middleware = suspend (state: State, action: Action, dispatcher: Dispatcher) -> Unit

class Store<S: State>(
    private val initialState: S,
    private val reducer: Reducer<S, Action>,
    private val middlewares: List<Middleware>
): CoroutineScope by CoroutineScope(Dispatchers.Main) {
    private val state = MutableStateFlow(initialState)

    fun observeState(): StateFlow<S> {
        return state
    }

    fun dispatch(action: Action) {
        val currentState = state.value

        val newState = reducer(currentState, action)

        if (newState != currentState) {
            state.value = newState
        }

        middlewares.forEach { middleware ->
            launch {
                middleware(state.value, action, ::dispatch)
            }
        }
    }
}

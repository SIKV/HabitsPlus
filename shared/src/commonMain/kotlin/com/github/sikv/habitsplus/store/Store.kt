package com.github.sikv.habitsplus.store

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

interface StoreState

interface Action

typealias Dispatcher = (action: Action) -> Unit

typealias Reducer<S, A> = (state: S, action: A) -> S

interface Middleware<S> {
    suspend fun invoke(state: S, action: Action, dispatcher: Dispatcher)
}

interface Effect

class Store<S: StoreState>(
    private val initialState: S,
    private val reducer: Reducer<S, Action>,
    private val middlewares: List<Middleware<S>>
): CoroutineScope by CoroutineScope(Dispatchers.Main) {

    private val state = MutableStateFlow(initialState)
    private val sideEffect = MutableSharedFlow<Effect>()

    fun observeState(): StateFlow<S> {
        return state
    }

    fun observeSideEffect(): Flow<Effect> {
        return sideEffect
    }

    fun dispatch(action: Action) {
        when (action) {
            is EmitSideEffectAction -> {
                launch {
                    sideEffect.emit(action.effect)
                }
            }
            else -> {
                val currentState = state.value
                val newState = reducer(currentState, action)

                if (newState != currentState) {
                    state.value = newState
                }

                middlewares.forEach { middleware ->
                    launch {
                        middleware.invoke(state.value, action, ::dispatch)
                    }
                }
            }
        }
    }
}

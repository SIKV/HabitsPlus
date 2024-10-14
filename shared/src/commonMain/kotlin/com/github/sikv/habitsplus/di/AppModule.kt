package com.github.sikv.habitsplus.di

import com.github.sikv.habitsplus.store.AppState
import com.github.sikv.habitsplus.store.AppStore
import com.github.sikv.habitsplus.store.Store
import com.github.sikv.habitsplus.store.appReducer
import org.koin.dsl.module

val appModule = module {
    single<AppStore> {
        Store(
            initialState = AppState.emptyState,
            reducer = appReducer,
            middlewares = listOf()
        )
    }
}

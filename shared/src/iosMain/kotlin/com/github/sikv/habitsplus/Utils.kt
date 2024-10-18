package com.github.sikv.habitsplus

import com.github.sikv.habitsplus.store.AppStore

fun AppStore.watchState() = observeState().wrap()

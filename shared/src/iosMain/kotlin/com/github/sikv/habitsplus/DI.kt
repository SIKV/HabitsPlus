package com.github.sikv.habitsplus

import com.github.sikv.habitsplus.di.appModule
import com.github.sikv.habitsplus.store.AppStore
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.context.startKoin

fun initKoin() {
    val koinApp = startKoin {
        modules(appModule)
    }.koin
}

object StoreProvider: KoinComponent {
    fun appStore(): AppStore = get()
}

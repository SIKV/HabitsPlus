package com.github.sikv.habitsplus

import com.github.sikv.habitsplus.di.appModule
import com.github.sikv.habitsplus.di.dataSourceModule
import com.github.sikv.habitsplus.di.databaseModule
import com.github.sikv.habitsplus.di.middlewareModule
import com.github.sikv.habitsplus.di.localPreferencesModule
import com.github.sikv.habitsplus.di.repositoryModule
import com.github.sikv.habitsplus.store.AppStore
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.context.startKoin

fun initKoin() {
    val koinApp = startKoin {
        modules(
            appModule,
            middlewareModule,
            databaseModule,
            dataSourceModule,
            repositoryModule,
            localPreferencesModule
        )
    }.koin
}

object StoreProvider: KoinComponent {
    fun appStore(): AppStore = get()
}

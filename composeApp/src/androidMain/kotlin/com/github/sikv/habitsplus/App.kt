package com.github.sikv.habitsplus

import android.app.Application
import com.github.sikv.habitsplus.di.appModule
import com.github.sikv.habitsplus.di.middlewareModule
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(appModule, middlewareModule)
        }
    }
}

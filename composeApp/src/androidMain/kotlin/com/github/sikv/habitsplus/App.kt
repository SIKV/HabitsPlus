package com.github.sikv.habitsplus

import android.app.Application
import com.github.sikv.habitsplus.di.appModule
import com.github.sikv.habitsplus.di.dataSourceModule
import com.github.sikv.habitsplus.di.databaseModule
import com.github.sikv.habitsplus.di.middlewareModule
import com.github.sikv.habitsplus.di.preferencesModule
import com.github.sikv.habitsplus.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                appModule,
                middlewareModule,
                databaseModule,
                dataSourceModule,
                repositoryModule,
                preferencesModule
            )
        }
    }
}

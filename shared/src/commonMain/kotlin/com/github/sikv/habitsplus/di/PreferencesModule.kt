package com.github.sikv.habitsplus.di

import com.github.sikv.habitsplus.data.preferences.LocalPreferences
import com.github.sikv.habitsplus.data.preferences.impl.LocalPreferencesImpl
import org.koin.dsl.module

val preferencesModule = module {
    single<LocalPreferences> {
        LocalPreferencesImpl()
    }
}

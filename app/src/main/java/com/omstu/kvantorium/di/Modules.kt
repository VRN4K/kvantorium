package com.omstu.kvantorium.di

import android.content.Context

import com.github.terrakok.cicerone.Cicerone
import com.omstu.kvantorium.MainActivityViewModel
import com.omstu.kvantorium.data.storage.preferences.BooleanPreference

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val ciceroneModule = module {
    val cicerone = Cicerone.create()
    single { cicerone.router }
    single { cicerone.getNavigatorHolder() }
}

val interactorModule = module {
    single { androidContext().resources }
    single {
        BooleanPreference(
            androidContext().getSharedPreferences(
                MainActivityViewModel.onboardingSharedPreferencesStore,
                Context.MODE_PRIVATE
            ), MainActivityViewModel.onboardingSharedPreferencesMap
        )
    }
}


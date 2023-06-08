package com.omstu.kvantorium.di

import android.content.Context

import com.github.terrakok.cicerone.Cicerone
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.omstu.kvantorium.MainActivityViewModel
import com.omstu.kvantorium.data.net.repositories.AuthRepository
import com.omstu.kvantorium.data.storage.preferences.UserAuthTokenPreference
import com.omstu.kvantorium.data.storage.preferences.base.BooleanPreference
import com.omstu.kvantorium.data.storage.preferences.repositories.UserAuthorizationPreferenceRepository
import com.omstu.kvantorium.data.storage.repositories.UserDBRepository
import com.omstu.kvantorium.domain.datacontracts.interactors.UserInteractor
import com.omstu.kvantorium.domain.datacontracts.interfaces.IAuthRepository
import com.omstu.kvantorium.domain.datacontracts.interfaces.IAuthorizationStorageRepository
import com.omstu.kvantorium.domain.datacontracts.interfaces.IUserDBRepository
import com.omstu.kvantorium.domain.datacontracts.interfaces.IUserInteractor

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val ciceroneModule = module {
    val cicerone = Cicerone.create()
    single { cicerone.router }
    single { cicerone.getNavigatorHolder() }
}


val firebaseModule = module {
    single {
        UserAuthTokenPreference(
            androidContext().getSharedPreferences(
                "USER_AUTH_KEY_STORE",
                Context.MODE_PRIVATE
            )
        )
    }
    single { FirebaseAuth.getInstance() }
    single { Firebase.database }
    single<IAuthorizationStorageRepository> {
        UserAuthorizationPreferenceRepository(
            get()
        )
    }
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
    single<IUserDBRepository> { UserDBRepository(get()) }
    single<IAuthRepository> { AuthRepository(get()) }
    single<IUserInteractor> { UserInteractor(get(), get(), get()) }

}

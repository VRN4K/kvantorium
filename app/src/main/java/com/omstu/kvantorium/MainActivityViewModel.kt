package com.omstu.kvantorium

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.omstu.kvantorium.presentation.base.BaseViewModel
import com.google.gson.Gson
import com.omstu.kvantorium.data.storage.preferences.BooleanPreference
import com.omstu.kvantorium.presentation.screens.Screens
import org.koin.core.component.inject

class MainActivityViewModel : BaseViewModel() {
    companion object {
        const val onboardingSharedPreferencesStore = "ONBOARDING_STORE"
        const val onboardingSharedPreferencesMap = "ONBOARDING_MAP"
    }

    private val sharedPreferences: BooleanPreference by inject()


    fun openRootScreen() {
        checkOnboardingShowing()
    }

    init {
        checkOnboardingShowing()
    }

    private fun checkOnboardingShowing() {
        var isOnboardingWasShown = Gson().fromJson(
            sharedPreferences.getValue().toString(),
            Boolean::class.java
        )

        router.newRootScreen(
            if (isOnboardingWasShown) checkIsUserAuthorized() else Screens.getOnboardingFragment()
        ).also {
            isOnboardingWasShown = true
        }

        //sharedPreferences.set(isOnboardingWasShown)
    }

    private fun checkIsUserAuthorized(): FragmentScreen {
        var isAuth = false

        if (isAuth) {
           return Screens.getUnauthorizedUserMainFragment()
        } else {
           TODO()
        }
    }
}
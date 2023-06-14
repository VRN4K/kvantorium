package com.omstu.kvantorium

import com.omstu.kvantorium.presentation.base.BaseViewModel
import com.google.gson.Gson
import com.omstu.kvantorium.data.storage.preferences.base.BooleanPreference
import com.omstu.kvantorium.domain.datacontracts.interfaces.IUserInteractor
import com.omstu.kvantorium.domain.datacontracts.utils.launchIO
import com.omstu.kvantorium.presentation.screens.Screens
import org.koin.core.component.inject

class MainActivityViewModel : BaseViewModel() {
    companion object {
        const val onboardingSharedPreferencesStore = "ONBOARDING_STORE"
        const val onboardingSharedPreferencesMap = "ONBOARDING_MAP"
    }

    private val sharedPreferences: BooleanPreference by inject()
    private val userInteractor: IUserInteractor by inject()


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
        launchIO {
            router.newRootScreen(
                if (isOnboardingWasShown) {
                    if (!userInteractor.isUserAuthorized()) {
                        Screens.getUnauthorizedUserMainFragment()
                    } else {
                        Screens.getMainFragment()
                    }
                } else {
                    Screens.getOnboardingFragment()
                }
            ).also {
                isOnboardingWasShown = true
            }
        }


        //sharedPreferences.set(isOnboardingWasShown)
    }
}
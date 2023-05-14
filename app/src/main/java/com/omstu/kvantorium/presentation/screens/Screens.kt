package com.omstu.kvantorium.presentation.screens

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.omstu.kvantorium.presentation.screens.onboardingscreen.OnboardingFragment
import com.omstu.kvantorium.presentation.screens.unauthorizedusermainscreen.UnauthorizedUserMainFragment

object Screens {
    fun getUnauthorizedUserMainFragment() = FragmentScreen { UnauthorizedUserMainFragment() }
    fun getOnboardingFragment() = FragmentScreen { OnboardingFragment() }
}
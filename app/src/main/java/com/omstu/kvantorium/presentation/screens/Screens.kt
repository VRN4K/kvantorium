package com.omstu.kvantorium.presentation.screens

import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.omstu.kvantorium.presentation.screens.onboardingpage.OnboardingFragment

object Screens {
    fun getMainFragment() = FragmentScreen { Fragment() }
    fun getOnboardingFragment() = FragmentScreen { OnboardingFragment() }
}
package com.omstu.kvantorium.presentation.screens.onboardingscreen

import com.omstu.kvantorium.presentation.base.BaseViewModel
import androidx.lifecycle.MutableLiveData
import com.omstu.kvantorium.R
import com.omstu.kvantorium.domain.datacontracts.model.OnboardingButtonItem
import com.omstu.kvantorium.domain.datacontracts.model.OnboardingItem

class OnboardingViewModel : BaseViewModel() {
    var onboardingData = MutableLiveData<MutableList<OnboardingItem>>()
    var onboardingButtonData = MutableLiveData<MutableList<OnboardingButtonItem>>()

    fun setOnboardingData() {
        onboardingData.postValue(
            mutableListOf(
                OnboardingItem(
                    R.string.onboarding_welcome_title,
                    R.string.onboarding_welcome_description,
                    R.drawable.logo
                ),
                OnboardingItem(
                    R.string.onboarding_schedule_title,
                    R.string.onboarding_schedule_description,
                    R.drawable.raspisanie
                ),
                OnboardingItem(
                    R.string.onboarding_notification_title,
                    R.string.onboarding_notification_description,
                    R.drawable.uvedomleniya
                )
            )
        )

        onboardingButtonData.postValue(
            mutableListOf(
                OnboardingButtonItem(
                    R.string.onboarding_next_button,
                    false
                ),
                OnboardingButtonItem(
                    R.string.onboarding_next_button,
                    false
                ),
                OnboardingButtonItem(
                    R.string.onboarding_lets_start_button,
                    true
                )
            )
        )
    }
}
package com.omstu.kvantorium.presentation.screens.unauthorizedusermainscreen

import androidx.lifecycle.MutableLiveData
import com.omstu.kvantorium.domain.datacontracts.model.OnboardingItem
import com.omstu.kvantorium.presentation.base.BaseViewModel

class UnauthorizedUserMainViewModel : BaseViewModel() {
    var onboardingData = MutableLiveData<MutableList<OnboardingItem>>()
}
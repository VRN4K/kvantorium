package com.omstu.kvantorium.presentation.screens.registrationscreens.singuppersonaldatascreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.omstu.kvantorium.domain.datacontracts.model.UserRegisterDataModel

class SingUpPersonalDataViewModelFactory(private val user: UserRegisterDataModel) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SingUpPersonalDataViewModel(user) as T
    }
}
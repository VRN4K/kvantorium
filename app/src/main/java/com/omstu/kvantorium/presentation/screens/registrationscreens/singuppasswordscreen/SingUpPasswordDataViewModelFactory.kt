package com.omstu.kvantorium.presentation.screens.registrationscreens.singuppasswordscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.omstu.kvantorium.domain.datacontracts.model.UserRegisterDataModel
import com.omstu.kvantorium.presentation.screens.registrationscreens.singuppersonaldatascreen.SingUpPersonalDataViewModel

class SingUpPasswordDataViewModelFactory (private val user: UserRegisterDataModel) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SingUpPasswordDataViewModel(user) as T
    }
}
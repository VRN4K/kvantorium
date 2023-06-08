package com.omstu.kvantorium.presentation.screens.registrationscreens.singupusernamescreen

import androidx.lifecycle.MutableLiveData
import com.omstu.kvantorium.presentation.base.BaseViewModel

class SingUpUserNameViewModel : BaseViewModel() {
    private var firstName = ""
    private var lastName = ""

    val userError = MutableLiveData<Boolean>()
    val userFirstName = MutableLiveData<String>()
    val userLastName = MutableLiveData<String>()

    fun setUserData() {
        userFirstName.postValue(firstName)
        userLastName.postValue(lastName)
    }

    fun confirmUserNameData(firstName: String, lastName: String) {
        val isNameEmpty = firstName.isEmpty()
        val isLastNameEmpty = lastName.isEmpty()

        if (!isNameEmpty && !isLastNameEmpty) {
            this.firstName = firstName
            this.lastName = lastName
            userError.postValue(false)
        } else {
            userError.postValue(true)
        }
    }
}
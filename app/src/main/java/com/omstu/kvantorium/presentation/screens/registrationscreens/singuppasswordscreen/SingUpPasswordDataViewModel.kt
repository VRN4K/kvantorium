package com.omstu.kvantorium.presentation.screens.registrationscreens.singuppasswordscreen

import androidx.lifecycle.MutableLiveData
import com.omstu.kvantorium.R
import com.omstu.kvantorium.domain.datacontracts.interfaces.IUserInteractor
import com.omstu.kvantorium.domain.datacontracts.launchIO
import com.omstu.kvantorium.domain.datacontracts.model.UserRegisterDataModel
import com.omstu.kvantorium.presentation.base.BaseViewModel
import org.koin.core.component.inject

class SingUpPasswordDataViewModel(private val user: UserRegisterDataModel) :
    BaseViewModel() {

    private val userInteractor: IUserInteractor by inject()
    private val passwordPattern = ("^" +
            "(?=.*[0-9])" +         //at least 1 digit
            "(?=.*[a-z])" +         //at least 1 lower case letter
            "(?=.*[A-Z])" +         //at least 1 upper case letter
            "(?=.*[a-zA-Z])" +      //any letter
            "(?=\\S+$)" +           //no white spaces
            ".{6,}" +               //at least 6 characters
            "$")

    val currentPasswordError = MutableLiveData<Int?>()
    val confirmPasswordError = MutableLiveData<Int?>()
    val userError = MutableLiveData<Boolean>()

    fun onRegisterButtonClick(currentPassword: String, confirmPassword: String) {

        if (currentPassword.getValidationCurrentPasswordResult()) {
            if (currentPassword.getValidationConfirmPasswordResult(confirmPassword)){
                launchIO {
                    userInteractor.registerNewUser(user, currentPassword)
                }
            }
        }
    }

    fun confirmUserPasswordsData(
        currentPassword: String,
        confirmPassword: String,
        isPersonalDataChecked: Boolean
    ) {
        val isCurrentPasswordEmpty = currentPassword.isNotEmpty()
        val isConfirmPasswordEmpty = confirmPassword.isNotEmpty()

        if ((isCurrentPasswordEmpty && isConfirmPasswordEmpty) && isPersonalDataChecked) {
            userError.postValue(false)
        } else {
            userError.postValue(true)
        }
    }

    private fun String.getValidationCurrentPasswordResult(): Boolean {
        var isValid = true
        when {
            this.isEmpty() -> currentPasswordError.postValue(R.string.sing_up_current_password_error)
                .also { isValid = false }
            !this.matches(passwordPattern.toRegex()) -> currentPasswordError.postValue(R.string.sing_up_current_password_error)
                .also { isValid = false }
            else -> currentPasswordError.postValue(null)
        }
        return isValid
    }

    private fun String.getValidationConfirmPasswordResult(
        confirmPassword: String
    ): Boolean {
        var isValid = true
        when {
            this != confirmPassword -> confirmPasswordError.postValue(R.string.sing_up_not_equals_passwords_error)
                .also { isValid = false }
            else -> confirmPasswordError.postValue(null)
        }
        return isValid
    }
}
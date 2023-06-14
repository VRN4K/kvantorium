package com.omstu.kvantorium.presentation.screens.singinscreen

import androidx.lifecycle.MutableLiveData
import com.omstu.kvantorium.R
import com.omstu.kvantorium.domain.datacontracts.interfaces.IUserInteractor
import com.omstu.kvantorium.domain.datacontracts.utils.launchIO
import com.omstu.kvantorium.presentation.base.BaseViewModel
import com.omstu.kvantorium.presentation.common.exceptions.WrongEmailOrPasswordException
import com.omstu.kvantorium.presentation.common.exceptions.createExceptionHandler
import com.omstu.kvantorium.presentation.screens.Screens
import org.koin.core.component.inject

class SingInViewModel : BaseViewModel() {
    private val userInteractor: IUserInteractor by inject()
    private val passwordPattern = ("^" +
            "(?=.*[0-9])" +         //at least 1 digit
            "(?=.*[a-z])" +         //at least 1 lower case letter
            "(?=.*[A-Z])" +         //at least 1 upper case letter
            "(?=.*[a-zA-Z])" +      //any letter
            "(?=\\S+$)" +           //no white spaces
            ".{6,}" +               //at least 6 characters
            "$")

    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    val passwordError = MutableLiveData<Int?>()
    val emailError = MutableLiveData<Int?>()
    val userNotFoundError = MutableLiveData<Int?>()
    val userError = MutableLiveData<Boolean>()

    fun onAuthButtonClick(email: String, password: String) {
        if (password.getValidationPasswordResult()) {

            launchIO(createExceptionHandler {
                if (it is WrongEmailOrPasswordException) {
                    println("Error")
                    userNotFoundError.postValue(R.string.sing_in_user_error)
                }
            }) {
                userInteractor.loginByEmailAndPassword(email,password)
                navigateToNewRoot(Screens.getMainFragment())
            }
        }
    }

    fun confirmUserDataFields(email: String, password: String) {
        val isEmailEmpty = email.isEmpty()
        val isPasswordEmpty = password.isEmpty()

        if (!isEmailEmpty && !isPasswordEmpty) {
            userError.postValue(false)
        } else {
            userError.postValue(true)
        }
    }

    private fun String.getValidationEmailResult(): Boolean {
        var isValid = true
        when {
            this.isEmpty() -> emailError.postValue(R.string.sing_up_email_error)
                .also { isValid = false }
            !this.matches(emailPattern.toRegex()) -> emailError.postValue(R.string.sing_up_email_error)
                .also { isValid = false }
            else -> emailError.postValue(null)
        }
        return isValid
    }

    private fun String.getValidationPasswordResult(): Boolean {
        var isValid = true
        when {
            this.isEmpty() -> passwordError.postValue(R.string.sing_up_current_password_error)
                .also { isValid = false }
            !this.matches(passwordPattern.toRegex()) -> passwordError.postValue(R.string.sing_up_current_password_error)
                .also { isValid = false }
            else -> passwordError.postValue(null)
        }
        return isValid
    }


}
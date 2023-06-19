package com.omstu.kvantorium.presentation.screens.updateemailscreen

import androidx.lifecycle.MutableLiveData
import com.omstu.kvantorium.R
import com.omstu.kvantorium.domain.datacontracts.interfaces.IUserInteractor
import com.omstu.kvantorium.domain.datacontracts.model.ProfileUserDataModel
import com.omstu.kvantorium.domain.datacontracts.utils.launchIO
import com.omstu.kvantorium.presentation.base.BaseViewModel
import org.koin.core.component.inject

class UpdateEmailViewModel : BaseViewModel() {
    private val userInteractor: IUserInteractor by inject()
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    private lateinit var user: ProfileUserDataModel
    val profileData = MutableLiveData<ProfileUserDataModel>()
    val emailError = MutableLiveData<Int?>()
    private lateinit var emailData: String

    init {
        setUserData()
    }

    private fun setUserData() {
        launchIO {
            user = userInteractor.getCurrentUser()
            profileData.postValue(user)
            emailData = user.userEmail
        }
    }

    fun saveNewEmail() {
        launchIO { userInteractor.updateUserEmail(userInteractor.getUserId()!!, emailData) }
    }

    fun validate(email: String) {
        emailData = email
        if (email.getValidationEmailResult()) saveNewEmail().also { navigateToPreviousScreen() }
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
}
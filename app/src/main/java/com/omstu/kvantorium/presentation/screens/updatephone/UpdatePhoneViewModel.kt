package com.omstu.kvantorium.presentation.screens.updatephone

import androidx.lifecycle.MutableLiveData
import com.omstu.kvantorium.R
import com.omstu.kvantorium.domain.datacontracts.interfaces.IUserInteractor
import com.omstu.kvantorium.domain.datacontracts.model.ProfileUserDataModel
import com.omstu.kvantorium.domain.datacontracts.utils.launchIO
import com.omstu.kvantorium.presentation.base.BaseViewModel
import org.koin.core.component.inject

class UpdatePhoneViewModel : BaseViewModel() {
    private val userInteractor: IUserInteractor by inject()

    private lateinit var user: ProfileUserDataModel
    val profileData = MutableLiveData<ProfileUserDataModel>()
    val phoneError = MutableLiveData<Int?>()
    private lateinit var phoneData: String

    init {
        setUserData()
    }

    private fun setUserData() {
        launchIO {
            user = userInteractor.getCurrentUser()
            profileData.postValue(user)
            phoneData = user.userPhoneNumber
        }
    }

    fun saveNewPhone() {
        launchIO { userInteractor.updateUserPhone(userInteractor.getUserId()!!, phoneData) }
    }

    fun validate(phone: String) {
        phoneData = phone
        if (phone.getValidationPhoneResult()) saveNewPhone().also { navigateToPreviousScreen() }
    }

    private fun String.getValidationPhoneResult(): Boolean {
        var isValid = true
        when {
            this == "" -> phoneError.postValue(R.string.sing_up_phone_error)
            this.length < 11 -> phoneError.postValue(R.string.sing_up_phone_error)
                .also { isValid = false }
            else -> phoneError.postValue(null)
        }
        return isValid
    }
}
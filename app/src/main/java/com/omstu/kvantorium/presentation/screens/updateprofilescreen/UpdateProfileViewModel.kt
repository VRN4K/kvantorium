package com.omstu.kvantorium.presentation.screens.updateprofilescreen

import androidx.lifecycle.MutableLiveData
import com.omstu.kvantorium.domain.datacontracts.interfaces.IUserInteractor
import com.omstu.kvantorium.domain.datacontracts.model.ProfileUserDataModel
import com.omstu.kvantorium.domain.datacontracts.utils.launchIO
import com.omstu.kvantorium.presentation.base.BaseViewModel
import org.koin.core.component.inject

class UpdateProfileViewModel : BaseViewModel() {

    private val userInteractor: IUserInteractor by inject()

    val profileData = MutableLiveData<ProfileUserDataModel>()


    init {
        setUserData()
    }

    fun setUserData() {
        launchIO {
            profileData.postValue(userInteractor.getCurrentUser())
        }
    }
}
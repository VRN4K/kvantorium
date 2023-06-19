package com.omstu.kvantorium.presentation.screens.profile

import androidx.lifecycle.MutableLiveData
import com.omstu.kvantorium.R
import com.omstu.kvantorium.domain.datacontracts.interfaces.IUserInteractor
import com.omstu.kvantorium.domain.datacontracts.model.ProfileScheduleDataModel
import com.omstu.kvantorium.domain.datacontracts.model.ProfileUserDataModel
import com.omstu.kvantorium.domain.datacontracts.utils.launchIO
import com.omstu.kvantorium.presentation.base.BaseViewModel
import com.omstu.kvantorium.presentation.screens.Screens
import org.koin.core.component.inject

class ProfileViewModel : BaseViewModel() {

    private val userInteractor: IUserInteractor by inject()

    private val scheduleItemsList = listOf(
        ProfileScheduleDataModel(
            "Космоквантум",
            listOf("Стрельба С. А.", "Кузнецов В. П."),
            R.drawable.course_item
        ),
        ProfileScheduleDataModel(
            "Аэроквантум",
            listOf("Томм В. В.", "Понятовская А. Г."),
            R.drawable.course_item_2
        )
    )

    val profileData = MutableLiveData<ProfileUserDataModel>()
    val coursesItems = MutableLiveData<List<ProfileScheduleDataModel>>()

    init {
        setUserData()
        coursesItems.postValue(scheduleItemsList)
    }

    fun onExitButtonClick() {
        launchIO {
            userInteractor.logout()
            navigateToNewRoot(Screens.getUnauthorizedUserMainFragment())
        }

    }

    fun setUserData() {
        launchIO {
            profileData.postValue(userInteractor.getCurrentUser())
        }
    }
}
package com.omstu.kvantorium.presentation.screens.registrationscreens.singuppersonaldatascreen

import androidx.lifecycle.MutableLiveData
import com.omstu.kvantorium.R
import com.omstu.kvantorium.domain.datacontracts.model.UserRegisterDataModel
import com.omstu.kvantorium.domain.datacontracts.model.UserSex
import com.omstu.kvantorium.domain.datacontracts.utils.DateTimeMapper
import com.omstu.kvantorium.presentation.base.BaseViewModel
import com.omstu.kvantorium.presentation.screens.Screens
import java.util.*

class SingUpPersonalDataViewModel(private val user: UserRegisterDataModel) :
    BaseViewModel() {
    private var email = ""
    private var date = ""
    private var phone = ""
    private var userSex = UserSex.MALE
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    val calendarBirthday by lazy { MutableLiveData<Calendar>() }
    val birthdayDateToOpenDialog = MutableLiveData(false)
    val birthdayText = MutableLiveData<String>()

    val emailError = MutableLiveData<Int?>()
    val phoneError = MutableLiveData<Int?>()
    val dateError = MutableLiveData<Int?>()

    val dateData = MutableLiveData("")
    val emailData = MutableLiveData("")
    val phoneData = MutableLiveData("")
    val userSexData = MutableLiveData(userSex)

    val toolbarTitle = MutableLiveData<String>()

    init {
        setToolbarText()
        calendarBirthday.value = Calendar.getInstance()
    }

    fun openStartDateDialog() {
        birthdayDateToOpenDialog.postValue(true)
    }

    fun setStartDate(year: Int, month: Int, day: Int) {
        calendarBirthday.value.apply {
            this!!.set(Calendar.DATE, day)
            set(Calendar.MONTH, month)
            set(Calendar.YEAR, year)
        }

        birthdayText.postValue(
            DateTimeMapper.format(
                calendarBirthday.value!!.time,
                DateTimeMapper.DATE_PATTERN
            )
        )
    }

    fun validate(date: String, email: String, phone: String, userSex: UserSex) {
        val validationResult = mutableListOf(
            email.getValidationEmailResult(),
            phone.getValidationPhoneResult()
        )

        if (!validationResult.contains(false)) {
            navigateToPasswordScreen(date, email, phone, userSex)
        }
    }

    fun setUserData() {
        dateData.postValue(date)
        emailData.postValue(email)
        phoneData.postValue(phone)
        userSexData.postValue(userSex)
    }

    private fun navigateToPasswordScreen(
        date: String,
        email: String,
        phone: String,
        userSex: UserSex
    ) {
        user.userEmail = email.also {
            this.email = it
        }
        user.userBirthdayDate = date.also {
            this.date = it
        }
        user.userPhoneNumber = phone.also {
            this.phone = it
        }
        user.userSex = userSex.also {
            this.userSex = it
        }

        navigateTo(Screens.getSingUpPasswordFragment(user))
    }

    private fun setToolbarText() {
        toolbarTitle.postValue("${user.userFirstName} ${user.userLastName}")
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

    private fun String.getValidationPhoneResult(): Boolean {
        var isValid = true
        when {
            this.length < 11 -> phoneError.postValue(R.string.sing_up_phone_error)
                .also { isValid = false }
            else -> phoneError.postValue(null)
        }
        return isValid
    }
}
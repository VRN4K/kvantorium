package com.omstu.kvantorium.domain.datacontracts.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class UserRegisterDataModel(
    var userFirstName: String? = null,
    var userLastName: String? = null,
    var userBirthdayDate: String? = null,
    var userEmail: String? = null,
    var userPhoneNumber: String? = null,
    var userSex: UserSex = UserSex.MALE
) : Parcelable

enum class UserSex(val value: String){
    MALE("Мужской"),
    FEMALE("Женский"),
}

@Parcelize
data class ProfileUserDataModel(
    var userFirstName: String,
    var userLastName: String,
    var userBirthdayDate: String,
    var userEmail: String,
    var userPhoneNumber: String,
    var userSex: UserSex
) : Parcelable
package com.omstu.kvantorium.data.storage.entities

import com.omstu.kvantorium.domain.datacontracts.model.ProfileUserDataModel
import com.omstu.kvantorium.domain.datacontracts.model.UserSex

data class UserEntity(
    val userFirstName: String,
    val userLastName: String,
    var userBirthday: String,
    var userPhoneNumber: String,
    var userEmail: String,
    var userSex: String,
)

fun UserEntity.toUI() : ProfileUserDataModel{
    return ProfileUserDataModel(
        this.userFirstName,
        this.userLastName,
        this.userBirthday,
        this.userEmail,
        this.userPhoneNumber,
        if (UserSex.MALE.value == this.userSex) UserSex.MALE else UserSex.FEMALE
    )
}





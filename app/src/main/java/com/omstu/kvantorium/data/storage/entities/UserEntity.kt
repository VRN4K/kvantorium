package com.omstu.kvantorium.data.storage.entities

import com.omstu.kvantorium.domain.datacontracts.model.UserSex

data class UserEntity(
    val userFirstName: String,
    val userLastName: String,
    var userBirthday: String,
    var userPhoneNumber: String,
    var userEmail: String,
    var userSex: String,

)





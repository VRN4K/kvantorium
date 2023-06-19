package com.omstu.kvantorium.domain.datacontracts.interfaces

import com.omstu.kvantorium.data.storage.entities.UserEntity

interface IUserDBRepository {
    suspend fun addUser(userId: String, userInfo: UserEntity)
    suspend fun getUserInfo(userId: String): UserEntity?
    suspend fun updateUserPhone(userId: String, phone: String)
    suspend fun updateUserEmail(userId: String, email: String)
    suspend fun updateUserBirthday(userId: String, birthday: String)
    fun deleteUserInfo(userId: String)
}
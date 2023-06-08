package com.omstu.kvantorium.domain.datacontracts.interfaces

import com.omstu.kvantorium.data.storage.entities.UserEntity

interface IUserDBRepository {
    suspend fun addUser(userId: String, userInfo: UserEntity)
    suspend fun getUserInfo(userId: String): UserEntity?
    fun updateUser(userId: String, levelValue: String)
    fun deleteUserInfo(userId: String)
}
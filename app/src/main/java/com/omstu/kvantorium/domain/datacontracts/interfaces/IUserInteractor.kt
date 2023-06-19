package com.omstu.kvantorium.domain.datacontracts.interfaces

import com.omstu.kvantorium.domain.datacontracts.model.ProfileUserDataModel
import com.omstu.kvantorium.domain.datacontracts.model.UserRegisterDataModel

interface IUserInteractor {
    suspend fun loginByEmailAndPassword(email: String, password: String)
    suspend fun isUserAuthorized(): Boolean
    suspend fun logout()
    suspend fun deleteAccount(): String?
    suspend fun registerNewUser(user: UserRegisterDataModel, password: String)
    fun getUserId(): String?
    suspend fun isCodeValid(code: String): Boolean
    suspend fun getCurrentUser(): ProfileUserDataModel
    suspend fun updateUserPhone(userId: String, phone: String)
    suspend fun updateUserEmail(userId: String, email: String)
    suspend fun updateUserBirthday(userId: String, birthday: String)
}
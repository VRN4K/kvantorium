package com.omstu.kvantorium.domain.datacontracts.interfaces

import com.omstu.kvantorium.domain.datacontracts.model.UserRegisterDataModel

interface IAuthRepository {
    suspend fun loginByEmailAndPassword(email: String, password: String): String?
    suspend fun logout()
    suspend fun resetPassword(code: String, newPassword: String)
    suspend fun updateUserPassword(currentPassword: String, newPassword: String)
    suspend fun deleteUser(): String?
    fun getUserId(): String?
    suspend fun registerNewUser(user: UserRegisterDataModel, password: String): String?
    suspend fun isCodeValid(code: String): Boolean
    suspend fun sendEmailResetPasswordMessage(email: String)
    suspend fun updateUserEmail(newEmail: String, currentPassword: String)
}
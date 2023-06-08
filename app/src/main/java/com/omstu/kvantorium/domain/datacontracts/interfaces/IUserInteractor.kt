package com.omstu.kvantorium.domain.datacontracts.interfaces

import com.omstu.kvantorium.data.storage.entities.UserEntity
import com.omstu.kvantorium.domain.datacontracts.model.UserRegisterDataModel

interface IUserInteractor {
    suspend fun loginByEmailAndPassword(email: String, password: String)
    suspend fun isUserAuthorized(): Boolean
    suspend fun logout()
    suspend fun deleteAccount(): String?
    suspend fun registerNewUser(user: UserRegisterDataModel, password: String)
    fun getUserId(): String?
    suspend fun isCodeValid(code: String): Boolean
    fun getCurrentUser(): UserEntity
    //suspend fun changeUserPassword(passwordChangeModel: PasswordChangeModel)
   // suspend fun updateUserInfo(updateUserEntity: UpdateUserEntity)
   // suspend fun resetPassword(code: String, newPassword: String)
   // suspend fun sendEmailResetPasswordMessage(email: String)
}
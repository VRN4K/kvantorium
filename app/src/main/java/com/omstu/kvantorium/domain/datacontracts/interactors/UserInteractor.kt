package com.omstu.kvantorium.domain.datacontracts.interactors

import com.omstu.kvantorium.data.storage.entities.UserEntity
import com.omstu.kvantorium.data.storage.entities.toUI
import com.omstu.kvantorium.domain.datacontracts.interfaces.IAuthorizationStorageRepository
import com.omstu.kvantorium.domain.datacontracts.interfaces.IUserDBRepository
import com.omstu.kvantorium.domain.datacontracts.interfaces.IAuthRepository
import com.omstu.kvantorium.domain.datacontracts.interfaces.IUserInteractor
import com.omstu.kvantorium.domain.datacontracts.model.ProfileUserDataModel
import com.omstu.kvantorium.domain.datacontracts.model.UserRegisterDataModel

class UserInteractor(
    private val authRepository: IAuthRepository,
    private val userDBRepository: IUserDBRepository,
    private val authPreferenceRepository: IAuthorizationStorageRepository,
) : IUserInteractor {
    private val userId = getUserId()
    private lateinit var userData: ProfileUserDataModel

    override suspend fun loginByEmailAndPassword(email: String, password: String) {
        val token = authRepository.loginByEmailAndPassword(email, password)
        authPreferenceRepository.saveToken(token)
    }

    override suspend fun isUserAuthorized(): Boolean {
        return !authPreferenceRepository.getToken().isNullOrEmpty()
    }

    override suspend fun registerNewUser(user: UserRegisterDataModel, password: String) {
        val userToken = authRepository.registerNewUser(user, password)
        if (!userToken.isNullOrEmpty()) {
            userDBRepository.addUser(
                getUserId()!!,
                UserEntity(
                    user.userFirstName!!,
                    user.userLastName!!,
                    user.userBirthdayDate.toString(),
                    user.userPhoneNumber!!,
                    user.userEmail!!,
                    user.userSex.value
                )
            )
        }
        authPreferenceRepository.saveToken(userToken)
    }


    override fun getUserId(): String? {
        return authRepository.getUserId()
    }

    override suspend fun isCodeValid(code: String): Boolean {
        return authRepository.isCodeValid(code)
    }

    override suspend fun getCurrentUser(): ProfileUserDataModel {
        val userId = authRepository.getUserId()

        userData = userDBRepository.getUserInfo(userId!!)!!.toUI()
        return userData

    }

    override suspend fun updateUserPhone(userId: String, phone: String) {
        userDBRepository.updateUserPhone(userId, phone)
    }

    override suspend fun updateUserEmail(userId: String, email: String) {
        userDBRepository.updateUserEmail(userId, email)
    }

    override suspend fun updateUserBirthday(userId: String, birthday: String) {
        userDBRepository.updateUserBirthday(userId, birthday)
    }

    override suspend fun logout() {
        authRepository.logout()
        authPreferenceRepository.saveToken(null)
    }

    override suspend fun deleteAccount(): String? {
        authRepository.deleteUser()?.let {
            userDBRepository.deleteUserInfo(it)
            authPreferenceRepository.saveToken(null)
            return it
        }
        return null
    }
}
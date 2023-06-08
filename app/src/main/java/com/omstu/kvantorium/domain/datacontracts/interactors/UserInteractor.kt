package com.omstu.kvantorium.domain.datacontracts.interactors

import com.omstu.kvantorium.data.storage.entities.UserEntity
import com.omstu.kvantorium.domain.datacontracts.interfaces.IAuthorizationStorageRepository
import com.omstu.kvantorium.domain.datacontracts.interfaces.IUserDBRepository
import com.omstu.kvantorium.domain.datacontracts.interfaces.IAuthRepository
import com.omstu.kvantorium.domain.datacontracts.interfaces.IUserInteractor
import com.omstu.kvantorium.domain.datacontracts.model.UserRegisterDataModel

class UserInteractor(
    private val authRepository: IAuthRepository,
    private val userDBRepository: IUserDBRepository,
    private val authPreferenceRepository: IAuthorizationStorageRepository,
) : IUserInteractor {

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
            //registerRequestData.userInfo.singUpDate = dateFormat.format(Calendar.getInstance().time)
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

    override fun getCurrentUser(): UserEntity {
        return UserEntity("","","","","","")
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
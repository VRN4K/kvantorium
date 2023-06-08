package com.omstu.kvantorium.data.storage.preferences.repositories

import com.omstu.kvantorium.data.storage.preferences.UserAuthTokenPreference
import com.omstu.kvantorium.domain.datacontracts.interfaces.IAuthorizationStorageRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserAuthorizationPreferenceRepository @Inject constructor(
    private val userAuthTokenPreference: UserAuthTokenPreference,
) : IAuthorizationStorageRepository {
    override fun saveToken(token: String?) {
        userAuthTokenPreference.set(token)
    }

    override fun getToken() = userAuthTokenPreference.get()
}
package com.omstu.kvantorium.domain.datacontracts.interfaces

interface IAuthorizationStorageRepository {
    fun saveToken(token: String?)
    fun getToken(): String?
}
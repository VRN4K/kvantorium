package com.omstu.kvantorium.data.storage.repositories

import android.util.Log
import com.google.firebase.database.FirebaseDatabase
import com.google.gson.Gson
import com.omstu.kvantorium.data.storage.entities.UserEntity
import com.omstu.kvantorium.domain.datacontracts.interfaces.IUserDBRepository
import org.koin.core.component.KoinComponent
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class UserDBRepository(private val database: FirebaseDatabase) :
    IUserDBRepository, KoinComponent {
    companion object {
        private const val DB_USER_REF_NAME = "USER"
    }

    private val dataBaseReference by lazy { database.getReference(DB_USER_REF_NAME) }

    override suspend fun addUser(userId: String, userInfo: UserEntity) {
        dataBaseReference.child(userId).setValue(userInfo).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d("USER", "addUserInfo:success")
            } else {
                Log.d("USER", "addUserInfo:failure", task.exception)
            }
        }
    }

    override suspend fun getUserInfo(userId: String): UserEntity? {
        val userInfo = mutableMapOf<String, String>()
        return suspendCoroutine {
            dataBaseReference.child(userId).get().addOnSuccessListener { dataSnapshot ->
                dataSnapshot.children.onEach { userData ->
                    userInfo[userData.key!!] = userData.value.toString()
                }
                it.resume(
                    UserEntity(
                        userInfo["userFirstName"]!!,
                        userInfo["userLastName"]!!,
                        userInfo["userBirthday"]!!,
                        userInfo["userPhoneNumber"]!!,
                        userInfo["userEmail"]!!,
                        userInfo["userSex"]!!
                    )
                )
            }.addOnFailureListener {
                Log.d("USER", "Error getting data", it)
            }
        }
    }

    override suspend fun updateUserPhone(userId: String, phone: String) {
        dataBaseReference.child(userId).child("userPhoneNumber").setValue(phone)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("USER", "addUserInfo:success")
                } else {
                    Log.d("USER", "addUserInfo:failure", task.exception)
                }
            }
    }

    override suspend fun updateUserEmail(userId: String, email: String) {
        dataBaseReference.child(userId).child("userEmail").setValue(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("USER", "addUserInfo:success")
                } else {
                    Log.d("USER", "addUserInfo:failure", task.exception)
                }
            }
    }

    override suspend fun updateUserBirthday(userId: String, birthday: String) {
        dataBaseReference.child(userId).child("userBirthday").setValue(birthday)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("USER", "addUserInfo:success")
                } else {
                    Log.d("USER", "addUserInfo:failure", task.exception)
                }
            }
    }

    override fun deleteUserInfo(userId: String) {
        dataBaseReference.child(userId).removeValue().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d("USER", "removeUserInfo:success")
            } else {
                Log.d("USER", "removeUser:failure", task.exception)
            }
        }
    }
}
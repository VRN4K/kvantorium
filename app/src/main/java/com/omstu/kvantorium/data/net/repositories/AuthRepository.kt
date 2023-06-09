package com.omstu.kvantorium.data.net.repositories

import android.util.Log
import com.google.firebase.auth.*
import com.omstu.kvantorium.domain.datacontracts.interfaces.IAuthRepository
import com.omstu.kvantorium.domain.datacontracts.model.UserRegisterDataModel
import org.koin.core.component.KoinComponent
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class AuthRepository(private val firebaseAuth: FirebaseAuth) : IAuthRepository, KoinComponent {
    override suspend fun loginByEmailAndPassword(email: String, password: String): String? {
        return suspendCoroutine {
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("Auth", "signInWithEmail:success")
                        Log.d("Auth", "user: ${firebaseAuth.currentUser!!.email}")
                        firebaseAuth.currentUser?.getIdToken(true)
                            ?.addOnCompleteListener { taskToken ->
                                it.resume(taskToken.result.token)
                            }
                    } else {
                        Log.d("Auth", "signInWithEmail:failure")
                        if (task.exception is FirebaseAuthInvalidUserException ||
                            task.exception is FirebaseAuthInvalidCredentialsException
                        ) {
                            //it.resumeWithException(WrongEmailOrPasswordException())
                        }
                    }
                }
        }
    }

    override suspend fun updateUserPassword(currentPassword: String, newPassword: String) {
        suspendCoroutine<Unit> {
            firebaseAuth.currentUser!!.reauthenticate(
                EmailAuthProvider.getCredential(
                    firebaseAuth.currentUser!!.email!!,
                    currentPassword
                )
            ).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("Auth", "changePassword: user re-authenticate")
                } else {
                    Log.d("Auth", "changePassword: user re-authentication failed")
                    //       it.resumeWithException(WrongCurrentPasswordException())
                }
            }

            firebaseAuth.currentUser!!.updatePassword(newPassword).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("Auth", "changePassword:success")
                    Log.d("Auth", "user: ${firebaseAuth.currentUser!!.email}")
                    it.resume(Unit)
                } else {
                    Log.d("Auth", "changePassword:failure", task.exception)
                }
            }
        }
    }

    override suspend fun registerNewUser(user: UserRegisterDataModel, password: String): String? {
        return suspendCoroutine {
            println("test 2")
            firebaseAuth.createUserWithEmailAndPassword(user.userEmail!!, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("Auth", "signUpWithEmail:success")
                        Log.d("Auth", "user: ${firebaseAuth.currentUser!!.email}")
                    } else {
                        Log.d("Auth", "signUpWithEmail:failure", task.exception)

                        if (task.exception is FirebaseAuthUserCollisionException) {
                            //   it.resumeWithException(UsernameOrEmailAlreadyExistException())
                        }
                    }
                    firebaseAuth.currentUser?.getIdToken(true)?.addOnCompleteListener { taskToken ->
                        it.resume(taskToken.result.token)
                    }
                }
        }
    }

    override fun getUserId(): String? {
        return firebaseAuth.currentUser?.uid
    }

    override suspend fun isCodeValid(code: String): Boolean {
        return suspendCoroutine {
            firebaseAuth.verifyPasswordResetCode(code).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("Auth", "checkResetPasswordEmailCode:success")
                    it.resume(true)
                } else {
                    Log.d("Auth", "checkResetPasswordEmailCode:failure", task.exception)
                    it.resume(false)
                }
            }
        }
    }

    override suspend fun sendEmailResetPasswordMessage(email: String) {
        suspendCoroutine<Unit> {
            firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("Auth", "sendResetPasswordEmail:success")
                    it.resume(Unit)

                } else {
                    Log.d("Auth", "sendResetPasswordEmail:failure", task.exception)
                    // it.resumeWithException(UserNotFoundException())
                }
            }
        }
    }

    override suspend fun logout() {
        firebaseAuth.signOut()
    }

    override suspend fun resetPassword(code: String, newPassword: String) {
        firebaseAuth.confirmPasswordReset(code, newPassword).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d("Auth", "resetPassword:success")
            } else {
                Log.d("Auth", "resetPassword:failure", task.exception)
            }
        }
    }

    override suspend fun deleteUser(): String? {
        val userId = getUserId()
        return suspendCoroutine {
            firebaseAuth.currentUser!!.delete().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("Auth", "userDeleting:success")
                    it.resume(userId)
                } else {
                    Log.d("Auth", "userDeleting:failure", task.exception)
                    it.resume(null)
                }
            }
        }
    }

    override suspend fun updateUserEmail(newEmail: String, currentPassword: String) {
        suspendCoroutine<Unit> { continuation ->
            firebaseAuth.currentUser!!.reauthenticate(
                EmailAuthProvider.getCredential(
                    firebaseAuth.currentUser!!.email!!,
                    currentPassword
                )
            ).addOnCompleteListener {
                firebaseAuth.currentUser?.let {
                    it.updateEmail(newEmail).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d("Auth", "userEmailUpdate:success")
                            continuation.resume(Unit)
                        } else {
                            Log.d("Auth", "userEmailUpdate:failure", task.exception)
                            //     continuation.resumeWithException(UsernameOrEmailAlreadyExistException())
                        }
                    }
                }
            }
        }
    }
}
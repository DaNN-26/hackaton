package com.example.hackaton.network.auth.data.repository

import com.example.hackaton.network.auth.domain.repository.AuthRepository
import com.example.hackaton.network.firestore.user.domain.model.UserData
import com.google.firebase.auth.FirebaseAuth
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class AuthRepositoryImpl(
    private val auth: FirebaseAuth
) : AuthRepository {
    override suspend fun signUp(email: String, password: String): UserData =
        suspendCoroutine { continuation ->
            auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener { result ->
                    val user = result.user
                    if(user != null) {
                        val userData = UserData(uid = user.uid, email = user.email ?: "")
                        continuation.resume(userData)
                    } else
                        continuation.resumeWithException(Exception("User is null"))
                }
                .addOnFailureListener { continuation.resumeWithException(it) }
        }

    override suspend fun signIn(email: String, password: String): UserData =
        suspendCoroutine { continuation ->
            auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener { result ->
                    val user = result.user
                    if(user != null) {
                        val userData = UserData(uid = user.uid, email = user.email ?: "")
                        continuation.resume(userData)
                    } else
                        continuation.resumeWithException(Exception("User is null"))
                }
                .addOnFailureListener { continuation.resumeWithException(it) }
        }
}
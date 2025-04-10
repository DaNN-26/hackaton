package com.example.hackaton.network.firestore.user.data.repository

import com.example.hackaton.network.firestore.user.domain.model.UserData
import com.example.hackaton.network.firestore.user.domain.repository.UserRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class UserRepositoryImpl(
    firestore: FirebaseFirestore
) : UserRepository {

    private val users = firestore.collection("users")

    override suspend fun createUser(user: UserData) {
        users.document(user.uid)
            .set(user)
    }

    override suspend fun getUser(uid: String): UserData =
        suspendCoroutine { continuation ->
            users.document(uid)
                .get()
                .addOnSuccessListener { snapshot ->
                    val userData = snapshot.toObject<UserData>()
                    continuation.resume(userData!!)
                }
                .addOnFailureListener { continuation.resumeWithException(it) }
        }
}
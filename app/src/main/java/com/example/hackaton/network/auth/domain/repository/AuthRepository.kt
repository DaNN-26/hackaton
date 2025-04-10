package com.example.hackaton.network.auth.domain.repository

import com.example.hackaton.network.firestore.user.domain.model.UserData
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    suspend fun signUp(email: String, password: String): UserData
    suspend fun signIn(email: String, password: String): UserData
}
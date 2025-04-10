package com.example.hackaton.network.firestore.user.domain.repository

import com.example.hackaton.network.firestore.user.domain.model.UserData

interface UserRepository {
    suspend fun createUser(user: UserData)
    suspend fun getUser(uid: String): UserData
}
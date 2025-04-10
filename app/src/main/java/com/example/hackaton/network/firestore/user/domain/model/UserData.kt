package com.example.hackaton.network.firestore.user.domain.model

data class UserData(
    val uid: String = "",
    val email: String = "",
    val firstname: String = "",
    val lastname: String = "",
    val patronymic: String = ""
)

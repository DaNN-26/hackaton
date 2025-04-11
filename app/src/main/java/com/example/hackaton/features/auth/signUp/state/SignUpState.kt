package com.example.hackaton.features.auth.signUp.state

import kotlinx.serialization.Serializable

@Serializable
data class SignUpState(
    val firstname: String = "",
    val lastname: String = "",
    val patronymic: String = ""
)

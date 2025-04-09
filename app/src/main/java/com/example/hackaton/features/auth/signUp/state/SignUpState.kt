package com.example.hackaton.features.auth.signUp.state

import kotlinx.serialization.Serializable

@Serializable
data class SignUpState(
    val email: String = "",
    val password: String = "",
    val repeatPassword: String = ""
)

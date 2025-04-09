package com.example.mvi.auth.signUp

import kotlinx.serialization.Serializable

@Serializable
data class SignUpState(
    val email: String = "",
    val password: String = "",
    val repeatPassword: String = ""
)

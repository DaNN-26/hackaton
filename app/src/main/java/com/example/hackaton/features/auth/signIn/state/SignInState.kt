package com.example.hackaton.features.auth.signIn.state

import kotlinx.serialization.Serializable

@Serializable
data class SignInState(
    val email: String = "",
    val password: String = ""
)

package com.example.hackaton.features.auth.signUp.inputEmail.state

import kotlinx.serialization.Serializable

@Serializable
data class InputEmailState(
    val email: String = "",
    val password: String = "",
    val repeatPassword: String = ""
)

package com.example.hackaton.features.auth.signUp.input_email.state

import kotlinx.serialization.Serializable

@Serializable
data class InputEmailState(
    val email: String = "",
    val password: String = "",
    val repeatPassword: String = ""
)

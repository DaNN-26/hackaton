package com.example.hackaton.features.auth.signUp.inputName.state

import kotlinx.serialization.Serializable

@Serializable
data class InputNameState(
    val firstname: String = "",
    val lastname: String = "",
    val patronymic: String = ""
)

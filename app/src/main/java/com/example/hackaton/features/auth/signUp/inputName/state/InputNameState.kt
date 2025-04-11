package com.example.hackaton.features.auth.signUp.inputName.state

import com.example.hackaton.features.auth.signUp.inputName.validator.InputNameValidator
import kotlinx.serialization.Serializable

@Serializable
data class InputNameState(
    val firstname: String = "",
    val lastname: String = "",
    val patronymic: String = "",
    val validator: InputNameValidator = InputNameValidator.Success
)

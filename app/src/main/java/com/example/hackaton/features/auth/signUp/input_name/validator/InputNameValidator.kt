package com.example.hackaton.features.auth.signUp.input_name.validator

import kotlinx.serialization.Serializable

@Serializable
sealed class InputNameValidator {
    @Serializable
    object Success : InputNameValidator()
    @Serializable
    object EmptyAllFields : InputNameValidator()
    @Serializable
    object EmptyFirstname : InputNameValidator()
    @Serializable
    object EmptyLastname : InputNameValidator()
    @Serializable
    object EmptyPatronymic : InputNameValidator()
}
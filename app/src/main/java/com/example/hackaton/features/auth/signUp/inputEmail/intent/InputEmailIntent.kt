package com.example.hackaton.features.auth.signUp.inputEmail.intent

sealed class InputEmailIntent {
    class OnEmailChange(val email: String): InputEmailIntent()
    class OnPasswordChange(val password: String): InputEmailIntent()
    class OnRepeatPasswordChange(val repeatPassword: String): InputEmailIntent()
    object SignUp: InputEmailIntent()
    object NavigateBack: InputEmailIntent()
}
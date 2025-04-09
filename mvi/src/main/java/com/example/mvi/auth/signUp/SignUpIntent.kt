package com.example.mvi.auth.signUp

sealed class SignUpIntent {
    class OnEmailChange(val email:String): SignUpIntent()
    class OnPasswordChange(val password:String): SignUpIntent()
    class OnRepeatPasswordChange(val password:String): SignUpIntent()
    object SignUp: SignUpIntent()
    object NavigateBack: SignUpIntent()
}
package com.example.mvi.auth.signIn

sealed class SignInIntent {
    class OnEmailChange(val email: String): SignInIntent()
    class OnPasswordChange(val password: String): SignInIntent()
    object SignIn: SignInIntent()
    object NavigateToSignUp: SignInIntent()
}
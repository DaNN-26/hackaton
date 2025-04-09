package com.example.hackaton.components.auth

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.example.hackaton.components.auth.signIn.SignInComponent
import com.example.hackaton.components.auth.signUp.SignUpComponent

interface AuthComponent {
    val stack: Value<ChildStack<*, Child>>

    sealed interface Child {
        class SignIn(val component: SignInComponent) : Child
        class SignUp(val component: SignUpComponent) : Child
    }
}
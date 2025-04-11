package com.example.hackaton.features.auth.component

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.example.hackaton.features.auth.signIn.component.SignInComponent
import com.example.hackaton.features.auth.signUp.component.SignUpComponent
import com.example.hackaton.features.auth.signUp.inputName.component.InputNameComponent

interface AuthComponent {
    val stack: Value<ChildStack<*, Child>>

    sealed interface Child {
        class SignIn(val component: SignInComponent) : Child
        class SignUp(val component: SignUpComponent) : Child
    }
}
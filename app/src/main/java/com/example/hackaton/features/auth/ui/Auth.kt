package com.example.hackaton.features.auth.ui

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.example.hackaton.features.auth.component.AuthComponent
import com.example.hackaton.features.auth.signIn.ui.SignInScreen
import com.example.hackaton.features.auth.signUp.ui.SignUpScreen

@Composable
fun Auth(
    component: AuthComponent
) {
    val stack = component.stack

    Children(
        stack = stack,
        animation = stackAnimation(fade())
    ) { child ->
        when (val instance = child.instance) {
            is AuthComponent.Child.SignIn -> SignInScreen(instance.component)
            is AuthComponent.Child.SignUp -> SignUpScreen(instance.component)
        }
    }
}
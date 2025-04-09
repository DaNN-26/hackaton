package com.example.hackaton.ui.view.auth

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.example.hackaton.components.auth.AuthComponent
import com.example.hackaton.ui.view.auth.signIn.SignInScreen
import com.example.hackaton.ui.view.auth.signUp.SignUpScreen

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
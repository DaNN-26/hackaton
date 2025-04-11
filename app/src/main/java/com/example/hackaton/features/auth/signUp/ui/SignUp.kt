package com.example.hackaton.features.auth.signUp.ui

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.example.hackaton.features.auth.signUp.component.SignUpComponent
import com.example.hackaton.features.auth.signUp.inputEmail.ui.InputEmailScreen
import com.example.hackaton.features.auth.signUp.inputName.ui.InputNameScreen

@Composable
fun SignUp(
    component: SignUpComponent
) {
    val stack = component.stack

    Children(
        stack = stack,
        animation = stackAnimation(slide())
    ) { child ->
        when (val instance = child.instance) {
            is SignUpComponent.Child.InputName -> InputNameScreen(instance.component)
            is SignUpComponent.Child.InputEmail -> InputEmailScreen(instance.component)
        }
    }
}
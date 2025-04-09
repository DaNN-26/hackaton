package com.example.hackaton.ui.view.root

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.example.hackaton.components.root.RootComponent
import com.example.hackaton.ui.view.auth.Auth
import com.example.hackaton.ui.view.main.Main

@Composable
fun Root(
    component: RootComponent
) {
    val stack = component.stack

    Children(
        stack = stack,
        animation = stackAnimation(fade())
    ) { child ->
        when (val instance = child.instance) {
            is RootComponent.Child.Auth -> Auth(instance.component)
            is RootComponent.Child.Main -> Main(instance.component)
        }
    }
}
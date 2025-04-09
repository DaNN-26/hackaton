package com.example.hackaton.features.main.ui

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.example.hackaton.features.main.component.MainComponent

@Composable
fun Main(
    component: MainComponent
) {
    val stack = component.stack

    Children(
        stack = stack,
        animation = stackAnimation(fade())
    ) { child ->
        when (val instance = child.instance) {
            is MainComponent.Child.Home -> {}
            is MainComponent.Child.Profile -> {}
        }
    }
}
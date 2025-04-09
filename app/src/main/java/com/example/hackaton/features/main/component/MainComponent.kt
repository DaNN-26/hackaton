package com.example.hackaton.features.main.component

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value

interface MainComponent {
    val stack: Value<ChildStack<*, Child>>

    sealed interface Child {
        object Home : Child
        object Profile : Child
    }
}
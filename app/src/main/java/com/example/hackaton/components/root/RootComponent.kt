package com.example.hackaton.components.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.example.hackaton.components.auth.AuthComponent
import com.example.hackaton.components.main.MainComponent

interface RootComponent {
    val stack: Value<ChildStack<*, Child>>

    sealed interface Child {
        class Auth(val component: AuthComponent) : Child
        class Main(val component: MainComponent) : Child
    }
}
package com.example.hackaton.features.root.component

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.example.hackaton.features.auth.component.AuthComponent
import com.example.hackaton.features.main.component.MainComponent

interface RootComponent {
    val stack: Value<ChildStack<*, Child>>

    sealed interface Child {
        class Auth(val component: AuthComponent) : Child
        class Main(val component: MainComponent) : Child
    }
}
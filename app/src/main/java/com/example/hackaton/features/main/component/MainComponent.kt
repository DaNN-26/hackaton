package com.example.hackaton.features.main.component

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.example.hackaton.features.main.home.component.HomeComponent

interface MainComponent {
    val stack: Value<ChildStack<*, Child>>

    sealed interface Child {
        class Home(val component: HomeComponent) : Child
        object Profile : Child
    }
}
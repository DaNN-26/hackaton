package com.example.hackaton.components.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.example.hackaton.components.auth.AuthComponent
import com.example.hackaton.components.auth.DefaultAuthComponent
import com.example.hackaton.components.main.DefaultMainComponent
import com.example.hackaton.components.main.MainComponent
import kotlinx.serialization.Serializable

class DefaultRootComponent(
    componentContext: ComponentContext
) : RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    override val stack = childStack(
        source = navigation,
        initialConfiguration = Config.Auth,
        serializer = Config.serializer(),
        handleBackButton = false,
        childFactory = ::child
    )

    private fun child(config: Config, componentContext: ComponentContext) =
        when (config) {
            Config.Auth -> RootComponent.Child.Auth(authComponent(componentContext))
            Config.Main -> RootComponent.Child.Main(mainComponent(componentContext))
        }

    private fun authComponent(componentContext: ComponentContext): AuthComponent =
        DefaultAuthComponent(componentContext)

    private fun mainComponent(componentContext: ComponentContext): MainComponent =
        DefaultMainComponent(componentContext)

    @Serializable
    sealed interface Config {
        @Serializable
        object Auth : Config
        @Serializable
        object Main : Config
    }
}
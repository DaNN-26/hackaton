package com.example.hackaton.features.main.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.example.hackaton.features.main.home.component.DefaultHomeComponent
import kotlinx.serialization.Serializable

class DefaultMainComponent(
    componentContext: ComponentContext
) : MainComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    override val stack = childStack(
        source = navigation,
        initialConfiguration = Config.Home,
        serializer = Config.serializer(),
        handleBackButton = true,
        childFactory = ::child
    )

    private fun child(config: Config, componentContext: ComponentContext) =
        when (config) {
            is Config.Home -> MainComponent.Child.Home(homeComponent(componentContext))
            is Config.Profile -> MainComponent.Child.Profile
        }

    private fun homeComponent(componentContext: ComponentContext) =
        DefaultHomeComponent(
            componentContext = componentContext
        )

    @Serializable
    sealed interface Config {
        @Serializable
        object Home : Config
        @Serializable
        object Profile : Config
    }
}
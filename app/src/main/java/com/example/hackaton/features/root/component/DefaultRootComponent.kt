package com.example.hackaton.features.root.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.example.hackaton.features.auth.component.AuthComponent
import com.example.hackaton.features.auth.component.DefaultAuthComponent
import com.example.hackaton.features.main.component.DefaultMainComponent
import com.example.hackaton.features.main.component.MainComponent
import com.example.hackaton.network.auth.domain.repository.AuthRepository
import com.example.hackaton.network.firestore.user.domain.repository.UserRepository
import kotlinx.serialization.Serializable

class DefaultRootComponent(
    componentContext: ComponentContext,
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
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
        DefaultAuthComponent(
            componentContext = componentContext,
            authRepository = authRepository,
            userRepository = userRepository
        )

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
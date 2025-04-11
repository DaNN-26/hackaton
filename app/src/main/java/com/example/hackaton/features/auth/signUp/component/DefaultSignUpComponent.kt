package com.example.hackaton.features.auth.signUp.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.DelicateDecomposeApi
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.example.hackaton.features.auth.signUp.inputEmail.component.DefaultInputEmailComponent
import com.example.hackaton.features.auth.signUp.inputName.component.DefaultInputNameComponent
import com.example.hackaton.network.auth.domain.repository.AuthRepository
import com.example.hackaton.network.firestore.user.domain.repository.UserRepository
import kotlinx.serialization.Serializable

class DefaultSignUpComponent(
    componentContext: ComponentContext,
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
) : SignUpComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    override val stack = childStack(
        source = navigation,
        initialConfiguration = Config.InputName,
        serializer = Config.serializer(),
        handleBackButton = true,
        childFactory = ::child
    )

    private fun child(config: Config, componentContext: ComponentContext) =
        when (config) {
            Config.InputName -> SignUpComponent.Child.InputName(inputNameComponent(componentContext))
            Config.InputEmail -> SignUpComponent.Child.InputEmail(inputEmailComponent(componentContext))
        }

    @OptIn(DelicateDecomposeApi::class)
    private fun inputNameComponent(componentContext: ComponentContext) =
        DefaultInputNameComponent(
            componentContext = componentContext,
            authRepository = authRepository,
            userRepository = userRepository,
            navigateNext = { navigation.push(Config.InputEmail) },
            navigateBack = { navigation.pop() },
        )

    private fun inputEmailComponent(componentContext: ComponentContext) =
        DefaultInputEmailComponent(
            componentContext = componentContext
        )

    @Serializable
    sealed interface Config {
        @Serializable
        object InputName : Config
        @Serializable
        object InputEmail : Config
    }
}
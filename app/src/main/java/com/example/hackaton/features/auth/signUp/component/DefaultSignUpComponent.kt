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
    private val userRepository: UserRepository,
    private val navigateBack: () -> Unit
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
            is Config.InputName -> SignUpComponent.Child.InputName(inputNameComponent(componentContext))
            is Config.InputEmail -> SignUpComponent.Child.InputEmail(inputEmailComponent(config, componentContext))
        }

    @OptIn(DelicateDecomposeApi::class)
    private fun inputNameComponent(componentContext: ComponentContext) =
        DefaultInputNameComponent(
            componentContext = componentContext,
            navigateToInputEmail = { navigation.push(Config.InputEmail(fullname = it)) },
            navigateBack = navigateBack,
        )

    private fun inputEmailComponent(config: Config.InputEmail, componentContext: ComponentContext) =
        DefaultInputEmailComponent(
            componentContext = componentContext,
            authRepository = authRepository,
            userRepository = userRepository,
            names = config.fullname,
            navigateBack = { navigation.pop() }
        )

    @Serializable
    sealed interface Config {
        @Serializable
        object InputName : Config
        @Serializable
        class InputEmail(val fullname: List<String>) : Config
    }
}
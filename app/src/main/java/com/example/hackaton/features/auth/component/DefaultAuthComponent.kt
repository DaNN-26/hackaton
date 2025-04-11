package com.example.hackaton.features.auth.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.DelicateDecomposeApi
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.example.hackaton.features.auth.signIn.component.DefaultSignInComponent
import com.example.hackaton.features.auth.signIn.component.SignInComponent
import com.example.hackaton.features.auth.signUp.component.DefaultSignUpComponent
import com.example.hackaton.features.auth.signUp.component.SignUpComponent
import com.example.hackaton.features.auth.signUp.inputName.component.DefaultInputNameComponent
import com.example.hackaton.features.auth.signUp.inputName.component.InputNameComponent
import com.example.hackaton.network.auth.domain.repository.AuthRepository
import com.example.hackaton.network.firestore.user.domain.repository.UserRepository
import kotlinx.serialization.Serializable

class DefaultAuthComponent(
    componentContext: ComponentContext,
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
) : AuthComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    override val stack = childStack(
        source = navigation,
        initialConfiguration = Config.SignIn,
        serializer = Config.serializer(),
        handleBackButton = true,
        childFactory = ::child
    )

    private fun child(config: Config, componentContext: ComponentContext) =
        when (config) {
            Config.SignIn -> AuthComponent.Child.SignIn(signInComponent(componentContext))
            Config.SignUp -> AuthComponent.Child.SignUp(signUpComponent(componentContext))
        }

    @OptIn(DelicateDecomposeApi::class)
    private fun signInComponent(componentContext: ComponentContext): SignInComponent =
        DefaultSignInComponent(
            componentContext = componentContext,
            authRepository = authRepository,
            userRepository = userRepository,
            navigateToSignUp = { navigation.push(Config.SignUp) }
        )

    private fun signUpComponent(componentContext: ComponentContext): SignUpComponent =
        DefaultSignUpComponent(
            componentContext = componentContext,
            authRepository = authRepository,
            userRepository = userRepository,
            navigateBack = { navigation.pop() }
        )

    @Serializable
    sealed interface Config {
        @Serializable
        object SignIn : Config
        @Serializable
        object SignUp : Config
    }
}
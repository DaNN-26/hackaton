package com.example.hackaton.components.auth

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.DelicateDecomposeApi
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.example.hackaton.components.auth.signIn.DefaultSignInComponent
import com.example.hackaton.components.auth.signIn.SignInComponent
import com.example.hackaton.components.auth.signUp.DefaultSignUpComponent
import com.example.hackaton.components.auth.signUp.SignUpComponent
import kotlinx.serialization.Serializable

class DefaultAuthComponent(
    componentContext: ComponentContext
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
            navigateToSignUp = { navigation.push(Config.SignUp) }
        )

    private fun signUpComponent(componentContext: ComponentContext): SignUpComponent =
        DefaultSignUpComponent(
            componentContext = componentContext,
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
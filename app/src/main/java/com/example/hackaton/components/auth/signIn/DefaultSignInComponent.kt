package com.example.hackaton.components.auth.signIn

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.update
import com.example.mvi.auth.signIn.SignInIntent
import com.example.mvi.auth.signIn.SignInState

class DefaultSignInComponent(
    componentContext: ComponentContext,
    private val navigateToSignUp: () -> Unit
) : SignInComponent, ComponentContext by componentContext {

    private val _state = MutableValue(
        stateKeeper.consume(SIGN_IN_COMPONENT, SignInState.serializer()) ?: SignInState()
    )

    override val state = _state

    override fun processIntent(intent: SignInIntent) {
        when (intent) {
            is SignInIntent.OnEmailChange -> _state.update { it.copy(email = intent.email) }
            is SignInIntent.OnPasswordChange -> _state.update { it.copy(password = intent.password) }
            is SignInIntent.SignIn -> { /*TODO*/ }
            is SignInIntent.NavigateToSignUp -> navigateToSignUp()
        }
    }

    companion object {
        const val SIGN_IN_COMPONENT = "SIGN_IN_COMPONENT"
    }
}
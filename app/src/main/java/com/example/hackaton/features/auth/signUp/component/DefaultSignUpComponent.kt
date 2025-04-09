package com.example.hackaton.features.auth.signUp.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.update
import com.example.hackaton.features.auth.signUp.intent.SignUpIntent
import com.example.hackaton.features.auth.signUp.state.SignUpState

class DefaultSignUpComponent(
    componentContext: ComponentContext,
    private val navigateBack: () -> Unit
) : SignUpComponent, ComponentContext by componentContext {

    private val _state = MutableValue(
        stateKeeper.consume(SIGN_UP_COMPONENT, SignUpState.serializer()) ?: SignUpState()
    )

    override val state = _state

    override fun processIntent(intent: SignUpIntent) {
        when (intent) {
            is SignUpIntent.OnEmailChange -> _state.update { it.copy(email = intent.email) }
            is SignUpIntent.OnPasswordChange -> _state.update { it.copy(password = intent.password) }
            is SignUpIntent.OnRepeatPasswordChange -> _state.update { it.copy(repeatPassword = intent.password) }
            is SignUpIntent.SignUp -> { /*TODO*/ }
            is SignUpIntent.NavigateBack -> navigateBack()
        }
    }

    companion object {
        const val SIGN_UP_COMPONENT = "SIGN_UP_COMPONENT"
    }
}
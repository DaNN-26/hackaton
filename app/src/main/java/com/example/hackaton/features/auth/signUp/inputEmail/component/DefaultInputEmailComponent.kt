package com.example.hackaton.features.auth.signUp.inputEmail.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import com.example.hackaton.features.auth.signUp.inputEmail.intent.InputEmailIntent
import com.example.hackaton.features.auth.signUp.inputEmail.state.InputEmailState

class DefaultInputEmailComponent(
    componentContext: ComponentContext
) : InputEmailComponent, ComponentContext by componentContext {

    private val _state = MutableValue(
        stateKeeper.consume(INPUT_EMAIL_COMPONENT, InputEmailState.serializer()) ?: InputEmailState()
    )

    override val state = _state

    override fun processIntent(intent: InputEmailIntent) {
        when (intent) {
            is InputEmailIntent.OnEmailChange -> _state.update { it.copy(email = intent.email) }
            is InputEmailIntent.OnPasswordChange -> _state.update { it.copy(password = intent.password) }
            is InputEmailIntent.OnRepeatPasswordChange -> _state.update { it.copy(repeatPassword = intent.repeatPassword) }
            is InputEmailIntent.SignUp -> { /*TODO*/ }
            is InputEmailIntent.NavigateBack -> { /*TODO*/ }
        }
    }

    companion object {
        const val INPUT_EMAIL_COMPONENT = "INPUT_EMAIL_COMPONENT"
    }
}
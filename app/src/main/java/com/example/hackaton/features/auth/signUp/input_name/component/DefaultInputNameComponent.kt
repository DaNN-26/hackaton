package com.example.hackaton.features.auth.signUp.input_name.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.update
import com.example.hackaton.features.auth.signUp.input_name.intent.InputNameIntent
import com.example.hackaton.features.auth.signUp.input_name.state.InputNameState
import com.example.hackaton.features.auth.signUp.input_name.validator.InputNameValidator

class DefaultInputNameComponent(
    componentContext: ComponentContext,
    private val navigateToInputEmail: (List<String>) -> Unit,
    private val navigateBack: () -> Unit
) : InputNameComponent, ComponentContext by componentContext {

    private val _state = MutableValue(
        stateKeeper.consume(INPUT_NAME_COMPONENT, InputNameState.serializer()) ?: InputNameState()
    )

    override val state = _state

    override fun processIntent(intent: InputNameIntent) {
        when (intent) {
            is InputNameIntent.OnFirstnameChange -> _state.update { it.copy(firstname = intent.firstname) }
            is InputNameIntent.OnLastnameChange -> _state.update { it.copy(lastname = intent.lastname) }
            is InputNameIntent.OnPatronymicChange -> _state.update { it.copy(patronymic = intent.patronymic) }
            is InputNameIntent.NavigateNext -> navigateNext()
            is InputNameIntent.NavigateBack -> navigateBack()
        }
    }

    private fun navigateNext() {
        checkInput()
        if(state.value.validator == InputNameValidator.Success) {
            val fullname = getFullName()
            navigateToInputEmail(fullname)
        }
    }

    private fun checkInput() {
        _state.update {
            it.copy(
                validator = when {
                    state.value.firstname.isEmpty() &&
                            state.value.lastname.isEmpty() &&
                            state.value.patronymic.isEmpty() ->
                                InputNameValidator.EmptyAllFields

                    state.value.firstname.isEmpty() -> InputNameValidator.EmptyFirstname
                    state.value.lastname.isEmpty() -> InputNameValidator.EmptyLastname
                    state.value.patronymic.isEmpty() -> InputNameValidator.EmptyPatronymic

                    else -> InputNameValidator.Success
                }
            )
        }
    }

    private fun getFullName() =
        listOf(state.value.firstname, state.value.lastname, state.value.patronymic).map { str ->
            str.trim()
                .substring(0, 1).uppercase() +
                    str.substring(1).lowercase()
        }

    companion object {
        const val INPUT_NAME_COMPONENT = "INPUT_NAME_COMPONENT"
    }
}
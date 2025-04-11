package com.example.hackaton.features.auth.signUp.inputName.component

import android.util.Log
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.update
import com.example.hackaton.features.auth.signUp.inputName.intent.InputNameIntent
import com.example.hackaton.features.auth.signUp.inputName.state.InputNameState
import com.example.hackaton.network.auth.domain.repository.AuthRepository
import com.example.hackaton.network.firestore.user.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DefaultInputNameComponent(
    componentContext: ComponentContext,
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository,
    private val navigateNext: () -> Unit,
    private val navigateBack: () -> Unit
) : InputNameComponent, ComponentContext by componentContext {

    private val _state = MutableValue(
        stateKeeper.consume(INPUT_NAME_COMPONENT, InputNameState.serializer()) ?: InputNameState()
    )

    override val state = _state

    private val scope = CoroutineScope(Dispatchers.IO)

    override fun processIntent(intent: InputNameIntent) {
        when (intent) {
            is InputNameIntent.OnFirstnameChange -> _state.update { it.copy(firstname = intent.firstname) }
            is InputNameIntent.OnLastnameChange -> _state.update { it.copy(lastname = intent.lastname) }
            is InputNameIntent.OnPatronymicChange -> _state.update { it.copy(patronymic = intent.patronymic) }
            is InputNameIntent.NavigateNext -> navigateNext()
            is InputNameIntent.NavigateBack -> navigateBack()
        }
    }

    private fun signUp() {
        scope.launch {
            try {
                val userData = authRepository
                    .signUp("12212@gmail.com", "123456")
                    .copy(
                        firstname = "Дмитрий",
                        lastname = "Ермохин",
                        patronymic = "Евгеньевич"
                    )
                userRepository.createUser(userData)
            } catch (e: Exception) {
                Log.d("SignUp", e.message.toString())
            }
        }
    }

    companion object {
        const val INPUT_NAME_COMPONENT = "INPUT_NAME_COMPONENT"
    }
}
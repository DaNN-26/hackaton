package com.example.hackaton.features.auth.signUp.input_email.component

import android.util.Log
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.update
import com.example.hackaton.features.auth.signUp.input_email.intent.InputEmailIntent
import com.example.hackaton.features.auth.signUp.input_email.state.InputEmailState
import com.example.hackaton.network.auth.domain.repository.AuthRepository
import com.example.hackaton.network.firestore.user.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DefaultInputEmailComponent(
    componentContext: ComponentContext,
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository,
    private val names: List<String>,
    private val navigateBack: () -> Unit
) : InputEmailComponent, ComponentContext by componentContext {

    private val _state = MutableValue(
        stateKeeper.consume(INPUT_EMAIL_COMPONENT, InputEmailState.serializer()) ?: InputEmailState()
    )

    override val state = _state

    private val scope = CoroutineScope(Dispatchers.IO)

    override fun processIntent(intent: InputEmailIntent) {
        when (intent) {
            is InputEmailIntent.OnEmailChange -> _state.update { it.copy(email = intent.email) }
            is InputEmailIntent.OnPasswordChange -> _state.update { it.copy(password = intent.password) }
            is InputEmailIntent.OnRepeatPasswordChange -> _state.update { it.copy(repeatPassword = intent.repeatPassword) }
            is InputEmailIntent.SignUp -> signUp()
            is InputEmailIntent.NavigateBack -> navigateBack()
        }
    }

    private fun signUp() {
        scope.launch {
            try {
                val userData = authRepository
                    .signUp(state.value.email, state.value.password)
                    .copy(
                        firstname = names[0],
                        lastname = names[1],
                        patronymic = names[2]
                    )
                userRepository.createUser(userData)
            } catch (e: Exception) {
                Log.d("SignUp", e.message.toString())
            }
        }
    }

    companion object {
        const val INPUT_EMAIL_COMPONENT = "INPUT_EMAIL_COMPONENT"
    }
}
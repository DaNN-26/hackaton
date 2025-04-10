package com.example.hackaton.features.auth.signUp.component

import android.util.Log
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.update
import com.example.hackaton.features.auth.signUp.intent.SignUpIntent
import com.example.hackaton.features.auth.signUp.state.SignUpState
import com.example.hackaton.network.auth.domain.repository.AuthRepository
import com.example.hackaton.network.firestore.user.domain.model.UserData
import com.example.hackaton.network.firestore.user.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DefaultSignUpComponent(
    componentContext: ComponentContext,
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository,
    private val navigateBack: () -> Unit
) : SignUpComponent, ComponentContext by componentContext {

    private val _state = MutableValue(
        stateKeeper.consume(SIGN_UP_COMPONENT, SignUpState.serializer()) ?: SignUpState()
    )

    override val state = _state

    private val scope = CoroutineScope(Dispatchers.IO)

    override fun processIntent(intent: SignUpIntent) {
        when (intent) {
            is SignUpIntent.OnEmailChange -> _state.update { it.copy(email = intent.email) }
            is SignUpIntent.OnPasswordChange -> _state.update { it.copy(password = intent.password) }
            is SignUpIntent.OnRepeatPasswordChange -> _state.update { it.copy(repeatPassword = intent.password) }
            is SignUpIntent.SignUp -> signUp()
            is SignUpIntent.NavigateBack -> navigateBack()
        }
    }

    private fun signUp() {
        scope.launch {
            try {
                val userData = authRepository
                    .signUp("example@gmail.com", "123456")
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
        const val SIGN_UP_COMPONENT = "SIGN_UP_COMPONENT"
    }
}
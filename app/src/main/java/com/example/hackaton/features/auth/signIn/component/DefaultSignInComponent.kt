package com.example.hackaton.features.auth.signIn.component

import android.util.Log
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.update
import com.example.hackaton.features.auth.signIn.intent.SignInIntent
import com.example.hackaton.features.auth.signIn.state.SignInState
import com.example.hackaton.network.auth.domain.repository.AuthRepository
import com.example.hackaton.network.firestore.user.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DefaultSignInComponent(
    componentContext: ComponentContext,
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository,
    private val navigateToSignUp: () -> Unit
) : SignInComponent, ComponentContext by componentContext {

    private val _state = MutableValue(
        stateKeeper.consume(SIGN_IN_COMPONENT, SignInState.serializer()) ?: SignInState()
    )

    override val state = _state

    private val scope = CoroutineScope(Dispatchers.IO)

    override fun processIntent(intent: SignInIntent) {
        when (intent) {
            is SignInIntent.OnEmailChange -> _state.update { it.copy(email = intent.email) }
            is SignInIntent.OnPasswordChange -> _state.update { it.copy(password = intent.password) }
            is SignInIntent.SignIn -> signIn()
            is SignInIntent.NavigateToSignUp -> navigateToSignUp()
        }
    }

    private fun signIn() {
        scope.launch {
            try {
                val userData = authRepository.signIn("example@gmail.com", "123456")
                val user = userRepository.getUser(userData.uid)
                Log.d("SignUp", user.email)
            } catch (e: Exception) {
                Log.d("SignInComponent", e.message.toString())
            }
        }
    }

    companion object {
        const val SIGN_IN_COMPONENT = "SIGN_IN_COMPONENT"
    }
}
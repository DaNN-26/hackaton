package com.example.hackaton.features.auth.signIn.component

import com.arkivanov.decompose.value.Value
import com.example.hackaton.features.auth.signIn.intent.SignInIntent
import com.example.hackaton.features.auth.signIn.state.SignInState

interface SignInComponent {
    val state: Value<SignInState>

    fun processIntent(intent: SignInIntent)
}
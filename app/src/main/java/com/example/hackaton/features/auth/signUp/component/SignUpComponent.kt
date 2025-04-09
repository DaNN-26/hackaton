package com.example.hackaton.features.auth.signUp.component

import com.arkivanov.decompose.value.Value
import com.example.hackaton.features.auth.signUp.intent.SignUpIntent
import com.example.hackaton.features.auth.signUp.state.SignUpState

interface SignUpComponent {
    val state: Value<SignUpState>

    fun processIntent(intent: SignUpIntent)
}
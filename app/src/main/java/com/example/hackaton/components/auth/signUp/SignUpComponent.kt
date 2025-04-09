package com.example.hackaton.components.auth.signUp

import com.arkivanov.decompose.value.Value
import com.example.mvi.auth.signUp.SignUpIntent
import com.example.mvi.auth.signUp.SignUpState

interface SignUpComponent {
    val state: Value<SignUpState>

    fun processIntent(intent: SignUpIntent)
}
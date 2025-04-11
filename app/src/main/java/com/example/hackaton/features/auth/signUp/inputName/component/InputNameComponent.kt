package com.example.hackaton.features.auth.signUp.inputName.component

import com.arkivanov.decompose.value.Value
import com.example.hackaton.features.auth.signUp.inputName.intent.InputNameIntent
import com.example.hackaton.features.auth.signUp.inputName.state.InputNameState

interface InputNameComponent {
    val state: Value<InputNameState>

    fun processIntent(intent: InputNameIntent)
}
package com.example.hackaton.features.auth.signUp.input_name.component

import com.arkivanov.decompose.value.Value
import com.example.hackaton.features.auth.signUp.input_name.intent.InputNameIntent
import com.example.hackaton.features.auth.signUp.input_name.state.InputNameState

interface InputNameComponent {
    val state: Value<InputNameState>

    fun processIntent(intent: InputNameIntent)
}
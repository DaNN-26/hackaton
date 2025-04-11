package com.example.hackaton.features.auth.signUp.input_email.component

import com.arkivanov.decompose.value.Value
import com.example.hackaton.features.auth.signUp.input_email.intent.InputEmailIntent
import com.example.hackaton.features.auth.signUp.input_email.state.InputEmailState

interface InputEmailComponent {
    val state: Value<InputEmailState>

    fun processIntent(intent: InputEmailIntent)
}
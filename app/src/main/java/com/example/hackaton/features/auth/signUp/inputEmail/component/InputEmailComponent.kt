package com.example.hackaton.features.auth.signUp.inputEmail.component

import com.arkivanov.decompose.value.Value
import com.example.hackaton.features.auth.signUp.inputEmail.intent.InputEmailIntent
import com.example.hackaton.features.auth.signUp.inputEmail.state.InputEmailState

interface InputEmailComponent {
    val state: Value<InputEmailState>

    fun processIntent(intent: InputEmailIntent)
}
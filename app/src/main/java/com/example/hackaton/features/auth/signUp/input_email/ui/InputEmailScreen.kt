package com.example.hackaton.features.auth.signUp.inputEmail.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.example.hackaton.features.auth.signUp.inputEmail.component.InputEmailComponent

@Composable
fun InputEmailScreen(
    component: InputEmailComponent
) {
    val state by component.state.subscribeAsState()
}
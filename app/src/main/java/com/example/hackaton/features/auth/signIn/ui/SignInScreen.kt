package com.example.hackaton.features.auth.signIn.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.example.hackaton.features.auth.signIn.component.SignInComponent
import com.example.hackaton.features.auth.signIn.intent.SignInIntent

@Composable
fun SignInScreen(
    component: SignInComponent
) {
    val state by component.state.subscribeAsState()

    Column() {
        TextField(
            value = state.email,
            onValueChange = { component.processIntent(SignInIntent.OnEmailChange(it)) }
        )

        Button(
            onClick = { component.processIntent(SignInIntent.NavigateToSignUp) }
        ) { }
    }
}
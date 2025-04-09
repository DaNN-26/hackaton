package com.example.hackaton.ui.view.auth.signIn

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.example.hackaton.components.auth.signIn.SignInComponent
import com.example.mvi.auth.signIn.SignInIntent

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
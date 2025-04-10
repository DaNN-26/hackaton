package com.example.hackaton.features.auth.signIn.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.example.hackaton.features.auth.signIn.component.SignInComponent
import com.example.hackaton.features.auth.signIn.intent.SignInIntent

@Composable
fun SignInScreen(
    component: SignInComponent
) {
    val state by component.state.subscribeAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { component.processIntent(SignInIntent.SignIn) }
        ) {
            Text("Sign In")
        }
        Button(onClick = { component.processIntent(SignInIntent.NavigateToSignUp) }) {
            Text("Navigate to Sign Up")
        }
    }
}
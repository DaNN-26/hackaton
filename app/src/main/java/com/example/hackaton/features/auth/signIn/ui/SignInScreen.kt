package com.example.hackaton.features.auth.signIn.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.example.hackaton.design.ui.components.MainButton
import com.example.hackaton.features.auth.signIn.component.SignInComponent
import com.example.hackaton.features.auth.signIn.intent.SignInIntent
import com.example.hackaton.features.auth.ui.components.AuthTextField

@Composable
fun SignInScreen(
    component: SignInComponent
) {
    val state by component.state.subscribeAsState()
    Content(
        email = state.email,
        password = state.password,
        onEmailChange = {component.processIntent(intent = SignInIntent.OnEmailChange(it))},
        onPasswordChange = {component.processIntent(intent = SignInIntent.OnPasswordChange(it))},
        navigateToNext = {component.processIntent(intent = SignInIntent.SignIn)},
        navigateToSignUp = {component.processIntent(intent = SignInIntent.NavigateToSignUp)}
    )
}

@Composable
private fun Content(
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    navigateToNext: () -> Unit,
    navigateToSignUp: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .padding(horizontal = 20.dp)
        ) {
            Title(
                modifier = Modifier.weight(0.4f)
            )
            InputForm(
                modifier = Modifier.weight(1f),
                email = email,
                password = password,
                onEmailChange = onEmailChange,
                onPasswordChange = onPasswordChange,
                onClick = navigateToNext,
            )
            BottomTextButton(
                onClick = navigateToSignUp,
                modifier = Modifier.weight(0.1f)
            )
        }
    }
}

@Composable
private fun Title(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = 10.dp,
            alignment = Alignment.CenterVertically
        )
    ) {
        Text(
            text = "EnergON",
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Для входа введите данные\nучётной записи",
            fontSize = 16.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun InputForm(
    modifier: Modifier = Modifier,
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        AuthTextField(
            value = email,
            onValueChange = { onEmailChange(it) },
            title = "Электронная почта",
            placeholderText = "example@mail.ru",
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            )
        )
        AuthTextField(
            value = password,
            onValueChange = { onPasswordChange(it) },
            title = "Пароль",
            placeholderText = "······",
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            isPassword = true
        )
        Spacer(Modifier.height(0.dp))
        MainButton(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth(),
            text = "Продолжить"
        )
    }
}

@Composable
private fun BottomTextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TextButton(
        onClick = onClick,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = "Нет учетной записи?",
            fontWeight = FontWeight.Bold,
            color = Color(112, 123, 129)
        )
        Text(
            text = "Зарегистрироваться",
            fontWeight = FontWeight.Bold,
            color = Color(43, 43, 43)
        )
    }
}

@Composable
@PreviewScreenSizes
fun PreviewScreenSize() {
    Content(
        email = "",
        password = "",
        onEmailChange = {},
        onPasswordChange = {},
        navigateToNext = {},
        navigateToSignUp = {}
    )
}


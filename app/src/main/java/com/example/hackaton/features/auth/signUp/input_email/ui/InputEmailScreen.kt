package com.example.hackaton.features.auth.signUp.input_email.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
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
import com.example.hackaton.features.auth.signUp.input_email.component.InputEmailComponent
import com.example.hackaton.features.auth.signUp.input_email.intent.InputEmailIntent
import com.example.hackaton.features.auth.ui.components.AuthTextField

@Composable
fun InputEmailScreen(
    component: InputEmailComponent
) {
    val state by component.state.subscribeAsState()
    Content(
        email = state.email,
        password = state.password,
        repeatPassword = state.repeatPassword,
        onEmailChange = {component.processIntent(intent = InputEmailIntent.OnEmailChange(it))},
        onPasswordChange = {component.processIntent(intent = InputEmailIntent.OnPasswordChange(it))},
        onRepeatPasswordChange = {component.processIntent(intent = InputEmailIntent.OnRepeatPasswordChange(it))},
        navigateToNext = {component.processIntent(intent = InputEmailIntent.SignUp)},
        navigateToBack = {component.processIntent(intent = InputEmailIntent.NavigateBack)}
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Content(
    email: String,
    password: String,
    repeatPassword: String,
    onRepeatPasswordChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    navigateToNext: () -> Unit,
    navigateToBack: () -> Unit
) {
    Scaffold(
        topBar = {
            IconButton(
                onClick = navigateToBack,
                modifier = Modifier
                    .windowInsetsPadding(TopAppBarDefaults.windowInsets)
                    .padding(start = 20.dp, top = 20.dp)
                    .size(45.dp),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Color(247, 247, 249)
                )
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                )
            }
        },
        modifier = Modifier.fillMaxSize()
    )
    { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .padding(horizontal = 20.dp)
        ) {
            Title(
                modifier = Modifier.weight(0.2f)
            )
            InputForm(
                modifier = Modifier.weight(1f),
                email = email,
                password = password,
                repeatPassword = repeatPassword,
                onRepeatPasswordChange = onRepeatPasswordChange,
                onEmailChange = onEmailChange,
                onPasswordChange = onPasswordChange,
                onClick = navigateToNext,
            )
            BottomTextButton(
                onClick = navigateToBack,
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
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = "Регистрация",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Введите данные для регистрации",
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
    repeatPassword: String,
    onRepeatPasswordChange: (String) -> Unit,
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
        AuthTextField(
            value = repeatPassword,
            onValueChange = { onRepeatPasswordChange(it) },
            title = "Повторите пароль",
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
            text = "Уже есть аккаунт?",
            fontWeight = FontWeight.Bold,
            color = Color(112, 123, 129)
        )
        Text(
            text = " Войти",
            fontWeight = FontWeight.Bold,
            color = Color(43, 43, 43)
        )
    }
}

@Composable
@PreviewScreenSizes
fun PreviewScreenSizes() {
    Content(
        email = "",
        password = "",
        repeatPassword = "",
        onRepeatPasswordChange = {},
        onEmailChange = {},
        onPasswordChange = {},
        navigateToNext = {},
        navigateToBack = {}
    )
}
package com.example.hackaton.features.auth.signUp.input_name.ui

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
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.example.hackaton.features.auth.signUp.input_name.component.InputNameComponent
import com.example.hackaton.features.auth.signUp.input_name.intent.InputNameIntent
import com.example.hackaton.design.ui.components.MainButton
import com.example.hackaton.design.ui.components.MainTextField
import com.example.hackaton.features.auth.signUp.input_name.validator.InputNameValidator
import com.example.hackaton.features.auth.ui.components.CustomSnackbar

@Composable
fun InputNameScreen(
    component: InputNameComponent
) {
    val state by component.state.subscribeAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(state.validator) {
        if(state.validator != InputNameValidator.Success)
            snackbarHostState.showSnackbar(
                message = when(state.validator) {
                    InputNameValidator.EmptyAllFields -> "Заполните все поля"
                    InputNameValidator.EmptyFirstname -> "Заполните поле Имя"
                    InputNameValidator.EmptyLastname -> "Заполните поле Фамилия"
                    InputNameValidator.EmptyPatronymic -> "Заполните поле Отчество"
                    else -> ""
                },
                duration = SnackbarDuration.Short
            )
    }

    Content(
        lastname = state.lastname,
        firstname = state.firstname,
        patronymic = state.patronymic,
        onFirstnameChange = { component.processIntent(InputNameIntent.OnFirstnameChange(it)) },
        onLastnameChange = { component.processIntent(InputNameIntent.OnLastnameChange(it)) },
        onPatronymicChange = { component.processIntent(InputNameIntent.OnPatronymicChange(it)) },
        navigateNext = {
            keyboardController?.hide()
            component.processIntent(InputNameIntent.NavigateNext)
        },
        navigateBack = { component.processIntent(InputNameIntent.NavigateBack) },
        snackbarHostState = snackbarHostState
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Content(
    lastname: String,
    firstname: String,
    patronymic: String,
    onFirstnameChange: (String) -> Unit,
    onLastnameChange: (String) -> Unit,
    onPatronymicChange: (String) -> Unit,
    navigateNext: () -> Unit,
    navigateBack: () -> Unit,
    snackbarHostState: SnackbarHostState
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            IconButton(
                onClick = navigateBack,
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
        snackbarHost = { CustomSnackbar(snackbarHostState) },
    ) { contentPadding ->
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
                lastname = lastname,
                firstname = firstname,
                patronymic = patronymic,
                onFirstnameChange = { onFirstnameChange(it) },
                onLastnameChange = { onLastnameChange(it) },
                onPatronymicChange = { onPatronymicChange(it) },
                onClick = navigateNext,
            )
            BottomTextButton(
                onClick = navigateBack,
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
            text = "Введите свои личные данные",
            fontSize = 16.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun InputForm(
    modifier: Modifier = Modifier,
    lastname: String,
    firstname: String,
    patronymic: String,
    onFirstnameChange: (String) -> Unit,
    onLastnameChange: (String) -> Unit,
    onPatronymicChange: (String) -> Unit,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        SignUpTextField(
            value = lastname,
            onValueChange = { onLastnameChange(it) },
            title = "Фамилия",
            placeholderText = "Иванов",
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            )
        )
        SignUpTextField(
            value = firstname,
            onValueChange = { onFirstnameChange(it) },
            title = "Имя",
            placeholderText = "Иван",
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            )
        )
        SignUpTextField(
            value = patronymic,
            onValueChange = { onPatronymicChange(it) },
            title = "Отчество",
            placeholderText = "Иванович",
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            )
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
private fun SignUpTextField(
    value: String,
    onValueChange: (String) -> Unit,
    title: String,
    placeholderText: String,
    keyboardOptions: KeyboardOptions
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = title,
            fontSize = 16.sp
        )
        MainTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = placeholderText)
            },
            keyboardOptions = keyboardOptions
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
fun Preview() {
    Content(
        snackbarHostState = SnackbarHostState(),
        lastname = "",
        firstname = "",
        patronymic = "",
        onFirstnameChange = {},
        onLastnameChange = {},
        onPatronymicChange = {},
        navigateNext = {},
        navigateBack = {}
    )
}
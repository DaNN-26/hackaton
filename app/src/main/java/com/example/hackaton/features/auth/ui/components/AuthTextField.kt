package com.example.hackaton.features.auth.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hackaton.R
import com.example.hackaton.design.ui.components.MainTextField

@Composable
fun AuthTextField(
    value: String,
    onValueChange: (String) -> Unit,
    isPassword: Boolean = false,
    title: String,
    placeholderText: String,
    keyboardOptions: KeyboardOptions,
) {
    var isPasswordVisible by remember { mutableStateOf(false) }
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
            trailingIcon = {
                if (isPassword) {
                    IconButton(
                        onClick = { isPasswordVisible = !isPasswordVisible },

                    ) {
                        Icon(
                            painter = painterResource(R.drawable.eye),
                            contentDescription = null,
                            tint = if (!isPasswordVisible) Color.Black else Color.Unspecified
                        )
                    }
                }
            },
            keyboardOptions = keyboardOptions,
            isPasswordVisible = isPasswordVisible
        )
    }
}

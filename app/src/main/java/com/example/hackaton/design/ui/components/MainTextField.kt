package com.example.hackaton.design.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MainTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: @Composable () -> Unit,
    keyboardOptions: KeyboardOptions
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(247, 247, 249),
            unfocusedContainerColor = Color(247, 247, 249),
            disabledContainerColor = Color(0xFFE0E0E0),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedLabelColor = Color.Gray,
            unfocusedLabelColor = Color.Gray,
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Gray
        ),
        placeholder = placeholder,
        keyboardOptions = keyboardOptions
    )
}
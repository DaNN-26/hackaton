package com.example.hackaton.features.auth.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomSnackbar(
    snackbarHostState: SnackbarHostState
) {
    SnackbarHost(snackbarHostState) { snackbarData ->
        Snackbar(
            modifier = Modifier.padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            containerColor = Color(247, 247, 249),
            contentColor = Color.Black
        ) {
            Text(text = snackbarData.visuals.message)
        }
    }
}
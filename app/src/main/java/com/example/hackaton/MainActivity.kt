package com.example.hackaton

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.arkivanov.decompose.defaultComponentContext
import com.example.hackaton.features.root.component.DefaultRootComponent
import com.example.hackaton.ui.theme.HackatonTheme
import com.example.hackaton.features.root.ui.Root

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HackatonTheme {
                Root(component = DefaultRootComponent(defaultComponentContext()))
            }
        }
    }
}
